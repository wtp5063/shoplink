package common;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Token
 */
@WebFilter("/Token")
public class Token implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
    HttpServletRequest req = (HttpServletRequest)request;
    if(req.getMethod().equals("GET")) {
      getToken(req);
    } else {
      if(!checkToken(req)) {
        throw new ServletException("不正なアクセスが行われました");
      }
    }
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

  public void getToken(HttpServletRequest request) {
    byte token[] = new byte[16];
    StringBuffer buff = new StringBuffer();
    SecureRandom random = null;
    try {
      random = SecureRandom.getInstance("SHA1PRNG");
      random.nextBytes(token);
      for(int i = 0; i < 16; i++) {
        buff.append(String.format("%02x", token[i]));
        HttpSession session = request.getSession();
        session.setAttribute("token", buff.toString());
      }
    } catch(NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private boolean checkToken(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String s_token = (String)session.getAttribute("token");
    if(s_token == null) {
      return false;
    }
    return true;
  }

}
