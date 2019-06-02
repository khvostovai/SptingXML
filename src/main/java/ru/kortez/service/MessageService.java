package ru.kortez.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kortez.DAO.MessageDao;
import ru.kortez.models.Message;


@Service
public class MessageService {
    @Autowired
    MessageDao messageDao;

    @Transactional
    public Message getMessage(int id) {
        return messageDao.findById(id);
    }

    @Transactional
    public void addMessage(Message message) {
        messageDao.createMessage(message);
    }

    @Transactional
    public void removeMessage(Message message) {
        messageDao.deleteMessage(message);
    }

    @Transactional
    public void changeMessage(Message message) {
        messageDao.updateMessage(message);
    }
}
