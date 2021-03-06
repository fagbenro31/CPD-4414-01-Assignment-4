/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import model.Account;

/**
 * Provides an Account Balance and Basic Withdrawal/Deposit Operations
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    Account acc = new Account();
    //protected Response doGet() {
    
    // ServletException throws an error when the servlet  has an error
    protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "private, no-store, nocache,must - revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter writer = response.getWriter();
        writer.write(String.valueOf(acc.getBalance()));
        writer.close();
        
    }
    
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameterMap().containsKey("deposit")) {
            double d = Double.parseDouble(request.getParameter("deposit"));
            acc.deposit(d);
        }
        
        if (request.getParameterMap().containsKey("withdraw")) {
            double w = Double.parseDouble(request.getParameter("withdraw"));
            acc.withdraw(w);
        }
        
        if (request.getParameterMap().containsKey("close")) {
            String c = request.getParameter("close");
            if (c.toLowerCase().equals("true")) {
                acc.close();
            }
        }
        
        
        
        
        
        
    }
}
