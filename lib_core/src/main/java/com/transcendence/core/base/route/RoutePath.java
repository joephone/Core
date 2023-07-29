package com.transcendence.core.base.route;

/**
 * @author joephone
 * @date 2022/11/29
 * @desc
 */
public class RoutePath {

    /**
     * Main组件
     */
    public static class Main {
        private static final String MAIN = "/module_main";

        /**
         * 主页面
         */
        public static final String PAGER_MAIN = MAIN + "/Main";
    }

    /**
     * web 组件
     */
    public static class Web {
        public static final String WEB = "/module_web";
        public static final String PAGER_WEB = WEB + "/Web";

    }

    /**
     * 首页组件
     */
    public static class Br {
        private static final String BR = "/module_br";

        /**
         * 首页
         */
        public static final String PAGER_BR = BR + "/Br";


        /**
         * 拒绝
         */
        public static final String PAGER_DENIED = BR + "/Denied";

        /**
         * 我的贷款
         */
        public static final String PAGER_MY_LOAN = BR + "/MyLoan";
        //0
        public static final String PAGER_LOAN_APPROVALING = BR + "/LoanApprovaling";
        //2
        public static final String PAGER_LOAN_APPROVALED = BR + "/LoanApprovaled";
    }

    /**
     * 首页组件
     */
    public static class Mine {
        private static final String MINE = "/module_mine";

        /**
         * 首页
         */
        public static final String PAGER_MINE = MINE + "/Mine";

        public static final String PAGER_MY_SCORE = MINE + "/MineScore";

        public static final String PAGER_MY_COLLECT = MINE + "/MineCollect";

        public static final String PAGER_MY_SHARE = MINE + "/MineShare";

        public static final String PAGER_ABOUT_AUTHOR = MINE + "/AboutAuthor";

        public static final String PAGER_INFO_GEN = MINE + "/AcInfoGen";
    }


    /**
     * 首页组件
     */
    public static class User {
        private static final String USER = "/module_user_center";

        /**
         * 首页
         */
        public static final String LOGIN = USER + "/Login";

    }

}
