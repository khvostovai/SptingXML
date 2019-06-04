package ru.kortez.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kortez.DAO.MessageDao;
import ru.kortez.models.Message;
import ru.kortez.models.Theme;

import java.util.List;


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

    @Transactional
    public List getMessageByTheme(Theme theme, int first, int pageSize) {
        return messageDao.getMessageByTheme(theme, first, pageSize);
    }

    @Transactional
    public int getCountPages(Theme theme, int pageSize) {
        return (int) Math.ceil(messageDao.getCountMessageByTheme(theme) / pageSize);
    }
}
