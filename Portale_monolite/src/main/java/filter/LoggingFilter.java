package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// Questa annotazione dice al server di intercettare TUTTE le rotte
@WebFilter("/*")
public class LoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
	    
	    HttpServletRequest req = (HttpServletRequest) request;
	    String path = req.getRequestURI();

	    // FILTRO: Logghiamo solo quello che NON è un file statico
	    boolean isStaticResource = path.endsWith(".css") || 
	                               path.endsWith(".png") || 
	                               path.endsWith(".jpg") || 
	                               path.endsWith(".js");

	    if (!isStaticResource) {
	        long startTime = System.currentTimeMillis();
	        System.out.println("[LOG APPLICATIVO] Richiesta: " + path);
	        
	        chain.doFilter(request, response);
	        
	        long duration = System.currentTimeMillis() - startTime;
	        System.out.println("[LOG APPLICATIVO] Completata in " + duration + " ms");
	    } else {
	        // I file statici passano silenziosamente senza sporcare la console
	        chain.doFilter(request, response);
	    }
	}
}