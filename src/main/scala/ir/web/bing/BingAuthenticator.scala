package ir.web.bing

import java.net.{Authenticator, PasswordAuthentication}

/**
 * <pre>
 * Created on 5/29/15.
 * </pre>
 * @author K.Sakamoto
 */
object BingAuthenticator {
  def setAuth(accountKey: String): Unit = {
    Authenticator.setDefault(new BingAuthenticator(accountKey))
  }
}

/**
  * @author K.Sakamoto
  * @param accountKey account key
  */
class BingAuthenticator(accountKey: String) extends Authenticator {
  override protected def getPasswordAuthentication: PasswordAuthentication = {
    val username: String = accountKey
    val password: String = accountKey
    new PasswordAuthentication(username, password.toCharArray)
  }
}
