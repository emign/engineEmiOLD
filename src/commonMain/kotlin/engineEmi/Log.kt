package engineEmi

import com.soywiz.klogger.Logger

object Log {
    val logger = Logger("engineEmig")
    fun log(s: String) {
        logger.log(Logger.Level.INFO) { s }
    }
}