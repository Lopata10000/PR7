package com.fanta.services;

import com.fanta.dao.PlayDao;
import com.fanta.entity.PlayEntity;

import java.util.List;

public final class PlayService {
    private static PlayService playService = new PlayService();

    private PlayService() {
    }

    public static PlayService getInstance() {
        return playService;
    }

    public void addNewPlay(PlayEntity playEntity) {
        PlayDao.getInstance().saveNewEntity(playEntity);
    }

    public void updatePlayById(int id, PlayEntity playEntity) {
        PlayDao.getInstance().updateEntityById(id, playEntity);
    }
}