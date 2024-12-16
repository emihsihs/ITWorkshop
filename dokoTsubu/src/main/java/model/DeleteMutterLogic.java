package model;

import dao.MuttersDAO;

public class DeleteMutterLogic {
    public boolean execute(int mutterId, String loginUserName) {
        MuttersDAO dao = new MuttersDAO();
        String postUserName = dao.findUserByMutterId(mutterId);

        if (postUserName == null || !postUserName.equals(loginUserName)) {
            System.out.println("ユーザーが一致しません。削除できません。");
            return false;
        }

        return dao.delete(mutterId);
    }
}
