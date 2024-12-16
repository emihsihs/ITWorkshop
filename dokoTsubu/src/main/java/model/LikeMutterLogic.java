package model;

import dao.LikesDAO;

public class LikeMutterLogic {
    public boolean execute(int mutterId, int userId) {
        LikesDAO dao = new LikesDAO();
        if (dao.isLiked(mutterId, userId)) {
            System.out.println("既にいいねされています。");
            return false;
        }
        return dao.insert(mutterId, userId);
    }
}
