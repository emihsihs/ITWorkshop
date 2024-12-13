package model;

import dao.CommentsDAO;
import dao.MuttersDAO;

public class DeleteMutterLogic {
    public void execute(int mutterId) {
        CommentsDAO commentsDAO = new CommentsDAO();
        commentsDAO.deleteByMutterId(mutterId); // 関連するコメントを削除
        MuttersDAO dao = new MuttersDAO();
        dao.delete(mutterId);
    }
}


