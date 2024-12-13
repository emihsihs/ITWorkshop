package model;

import java.util.List;

import dao.MuttersDAO;

public class GetMutterListLogic {
    public List<Mutter> execute() {
        MuttersDAO dao = new MuttersDAO();
        List<Mutter> mutterList = dao.findAll();
        for (Mutter mutter : mutterList) {
            GetCommentListLogic getCommentListLogic = new GetCommentListLogic();
            List<Comment> commentList = getCommentListLogic.execute(mutter.getId());
            mutter.setComments(commentList);
        }
        return mutterList;
    }
}

