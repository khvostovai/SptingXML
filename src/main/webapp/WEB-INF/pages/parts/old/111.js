let history=[];// stack for history
// when page was load, add buttons
$(document).ready(function(){
    //drow root level
    drowRequirementsByPID(0);

    // bind event for show modal window
    // action and pid depend of button with data-target = modal
    $("#addReq").on('show.bs.modal', function(event){
        showModal(event);
    });
});

function drowRequirementsByPID(pid){
    let reqData = $("#reqData");
    //save in stack
    history.push(pid);
    reqData.empty();
    // top level
    if(pid===0){
        // remove button Back
        $('button.backButton').remove();
    }
    else{
        // remove old button Back
        $(".backButton").remove();
        // drow button Back
        let back = '<button class="btn btn-primary backButton" type="button" ';
        back += 'onClick="backButtonClick()">Back</button>';
        $("#divBack").append(back);
        // drow info parrent requirement
        $.getJSON({
            url: '/getreq',
            data: 'id='+ pid,
            success: function(data){
                // header
                let div = '<p class="text-center"><b>[' + data[0].id + '] ' + data[0].title + '</b></p>';
                div += '<div class="container">';
                div += data[0].requirement;
                div += '<hr>';
                div += '</div>';
                // other info
                div += '<div class="row text-center">';
                // first block
                div += '<div class="col">';
                div += 'type: ' + encryptTypeReq(data[0].type_id);
                div += '</div>';
                // second block
                div += '<div class="col">';
                div += 'priority: ' + data[0].priority;
                div += '</div>';
                // third block
                div += '<div class="col">';
                div += 'complite date: ' + data[0].complite_date;
                div += '</div>';
                // end of row
                div += '</div>';
                div += '<hr><br>';
                reqData.append(div);
            }
        });
    }
    // for request JSON, send parrent id of requirements
    let parameters = {
        pid: pid
    };

    // get requirements with pid 0 and drow its
    $.getJSON("/getreq", parameters, function(data, textStatus, jqXHR){
        let div = $("#buttons");

        // remove all button with requirement
        $('div.reqButton').remove();
        // for each req
        data.forEach(function(item){
            // main block
            let block = '<div class="container row reqButton">';
            // button block
            block += '<div class = "col">';
            block += '<button class="container" type="button" ';
            // add event for buttons
            block += 'onClick="drowRequirementsByPID(' + item.id + ');">';
            block += '<div class="row">';
            //first column
            block += '<div class="col-md-auto text-left">';
            block += '<p>[' + item.id + ']</p>';
            block += '<p>' + encryptTypeReq(item.type_id) + '</p>';
            block += '</div>';
            //second column
            block += '<div class="col">' + item.title + '</div>';
            //third column
            block += '<div class="col-md-auto text-right">';
            block += '<p>from:' + item.create_date + '</p>';
            block += '<p>to:' + item.complite_date + '</p>';
            block += '</div>';
            block += '</div>' + '</button>';
            block += '</div>';
            // end button block
            // action button block
            block += '<div class="col-md-auto">';
            // button edit
            block += '<button id="editButton" class= "btn btn-link" type="button"';
            block += 'data-toggle="modal" data-id="'+ item.id +'" data-action="/edit" data-target="#addReq"';
            block += '>edit</button>';
            block += '</br>';
            // button delete
            block += '<button id="deleteButton" class= "btn btn-link" type="button"';
            block += 'onClick="deleteReq('+ item.id +')">delete</button>';
            block += '</div>';
            // end action button block
            block += '</div>';
            // end main block

            div.append(block);
        });
    });
}

function showModal(event){
    // get action and pid for send to server
    let action  = $(event.relatedTarget).data('action');
    // pid is top of history
    //let pid = $(event.relatedTarget).data('pid');
    let pid = history[history.length-1];
    let id = $(event.relatedTarget).data('id');
    // else action is /edit then get data from server and put it to the modal window
    if(action == "/edit"){
        // prepare parameter for ajax request
        let parameters = {
            id: id
        };
        // ajax request single item with id
        $.getJSON("/getreq", parameters, function(data, textStatus, jqXHR){
            //insert data from request to modal window
            item = data[0];
            $('#type').val(item.type_id).prop('selected',true);
            $('#priority').val(item.priority).prop('selected',true);
            $('#complite_date').val(item.complite_date);
            $('#title').val(item.title);
            $('#requirement').val(item.requirement);
        });

    }
    //erase all fields
    else{
        $('#type').val(0).prop('selected',true);
        $('#priority').val(0).prop('selected',true);
        $('#title').val('');
        $('#requirement').val('');
    }
    // unbind all
    $("#actionModalButton").unbind();
    // bind event for active button at modal window
    $("#actionModalButton").on('click', function(){
        // ensure empty data
        if (checkField($('#title'))         == false) return false;
        if (checkField($('#requirement'))   == false) return false;
        // prepare data for send to server
        data =  'parent_id='+ pid +'&';
        data += 'id=' + id +'&';
        data += 'type='+ $('#type').val() + '&';
        data += 'title='+ $('#title').val() + '&';
        data += 'priority='+ $('#priority').val() + '&';
        data += 'complite_date='+ $('#complite_date').val() + '&';
        data += 'requirement='+ $('#requirement').val();
        sendRequestToServer(data, action);
    });
}

//function for check field on empty
function checkField(item){
    if (item.val() == '')
    {
        item.addClass("border border-danger");
        return false;
    }
    else{
        item.removeClass("border border-danger");
        return true;
    }
}

// function for back button
function backButtonClick(){
    //delete from stack
    history.pop();
    drowRequirementsByPID(history.pop());
}
// function for reqButtons
function reqButtonClick(pid, pid_back){
    //save in stack
    history.push(pid_back);
    drowRequirementsByPID(pid);
}

//function for add or edit requirement
function sendRequestToServer(data, action){
    // send data to server
    $.ajax({
        url: action,    // '/edit' or '/add'
        type: "POST",
        data: data,
        success: function(data){
            $("#addReq").modal('hide');
            //redrow
            reDrowReq();
        }
    });
}

//function for delete requirement with id
function deleteReq(id){
    $.ajax({
        url: "/delete",
        type: "POST",
        data: 'id=' + id,
        success: function(data){
            //redrow
            reDrowReq();
        }
    });
}

//function for re drow current level after action with requirements
function reDrowReq(){
    drowRequirementsByPID(history.pop());
}

function encryptTypeReq(num){
    if (num == 0) return "to be difine";
    else if(num == 1) return "functional";
    else if(num == 2) return "construct";
    else if(num == 3) return "interface";
}