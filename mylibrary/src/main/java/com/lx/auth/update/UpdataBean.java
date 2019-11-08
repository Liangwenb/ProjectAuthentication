package com.lx.auth.update;

public class UpdataBean {

    /**
     * code : 0
     * message :
     * data : {"buildBuildVersion":"6","forceUpdateVersion":"","forceUpdateVersionNo":"","needForceUpdate":false,"downloadURL":"https://www.pgyer.com/app/installUpdate/772043ce10371f212ca4b18f35f40411?sig=qoxsrBYEvfvVFdhJs%2BUY9DcHpfh6pmtI7Wu8TBq0d8am8KJM8HkJG71DI5vWoFQ6","buildHaveNewVersion":true,"buildVersionNo":"2","buildVersion":"1.0.2","buildShortcutUrl":"https://www.pgyer.com/DKss","buildUpdateDescription":""}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * buildBuildVersion : 6
         * forceUpdateVersion :
         * forceUpdateVersionNo :
         * needForceUpdate : false
         * downloadURL : https://www.pgyer.com/app/installUpdate/772043ce10371f212ca4b18f35f40411?sig=qoxsrBYEvfvVFdhJs%2BUY9DcHpfh6pmtI7Wu8TBq0d8am8KJM8HkJG71DI5vWoFQ6
         * buildHaveNewVersion : true
         * buildVersionNo : 2
         * buildVersion : 1.0.2
         * buildShortcutUrl : https://www.pgyer.com/DKss
         * buildUpdateDescription :
         */

        private String buildBuildVersion;
        private String forceUpdateVersion;
        private String forceUpdateVersionNo;
        private boolean needForceUpdate;
        private String downloadURL;
        private boolean buildHaveNewVersion;
        private String buildVersionNo;
        private String buildVersion;
        private String buildShortcutUrl;
        private String buildUpdateDescription;

        public String getBuildBuildVersion() {
            return buildBuildVersion;
        }

        public void setBuildBuildVersion(String buildBuildVersion) {
            this.buildBuildVersion = buildBuildVersion;
        }

        public String getForceUpdateVersion() {
            return forceUpdateVersion;
        }

        public void setForceUpdateVersion(String forceUpdateVersion) {
            this.forceUpdateVersion = forceUpdateVersion;
        }

        public String getForceUpdateVersionNo() {
            return forceUpdateVersionNo;
        }

        public void setForceUpdateVersionNo(String forceUpdateVersionNo) {
            this.forceUpdateVersionNo = forceUpdateVersionNo;
        }

        public boolean isNeedForceUpdate() {
            return needForceUpdate;
        }

        public void setNeedForceUpdate(boolean needForceUpdate) {
            this.needForceUpdate = needForceUpdate;
        }

        public String getDownloadURL() {
            return downloadURL;
        }

        public void setDownloadURL(String downloadURL) {
            this.downloadURL = downloadURL;
        }

        public boolean isBuildHaveNewVersion() {
            return buildHaveNewVersion;
        }

        public void setBuildHaveNewVersion(boolean buildHaveNewVersion) {
            this.buildHaveNewVersion = buildHaveNewVersion;
        }

        public String getBuildVersionNo() {
            return buildVersionNo;
        }

        public void setBuildVersionNo(String buildVersionNo) {
            this.buildVersionNo = buildVersionNo;
        }

        public String getBuildVersion() {
            return buildVersion;
        }

        public void setBuildVersion(String buildVersion) {
            this.buildVersion = buildVersion;
        }

        public String getBuildShortcutUrl() {
            return buildShortcutUrl;
        }

        public void setBuildShortcutUrl(String buildShortcutUrl) {
            this.buildShortcutUrl = buildShortcutUrl;
        }

        public String getBuildUpdateDescription() {
            return buildUpdateDescription;
        }

        public void setBuildUpdateDescription(String buildUpdateDescription) {
            this.buildUpdateDescription = buildUpdateDescription;
        }
    }
}
