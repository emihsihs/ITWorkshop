package model;

import dao.LikesDAO;

public class UnlikeMutterLogic {
    public boolean execute(int mutterId, int userId) {
        LikesDAO dao = new LikesDAO();
        return dao.delete(mutterId, userId);
    }
}
