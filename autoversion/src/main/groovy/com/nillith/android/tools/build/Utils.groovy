package com.nillith.android.tools.build


class Utils {
    static int getCommitCount() {
        try {
            def result = new StringBuilder()
            def error = new StringBuilder()
            def process = 'git rev-list HEAD --count'.execute()
            process.waitForProcessOutput(result, error)
            if (result.isInteger()) {
                return result.toInteger()
            } else {
                Messager.info("error:${error}")
                return 0
            }
        } catch (Exception e) {
            Messager.info(e)
        }
        return 0
    }

}
