package com.br.csi.gda.controller;

import com.br.csi.gda.dao.VoluntarioDAO;
import com.br.csi.gda.model.Permissao;
import com.br.csi.gda.model.Usuario;
import com.br.csi.gda.model.Voluntario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("VoluntarioController")
public class VoluntarioController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao;
        String retorno;
        opcao = req.getParameter("opcao");
        if(opcao != null) {
            if (opcao.equals("CAD") || opcao.equals("EDTV")) {
                String nome = req.getParameter("nome");
                String cpf = req.getParameter("cpf");
                String idade = req.getParameter("idade");
                String endereco = req.getParameter("endereco");

                Permissao p = new Permissao(3, "VOLUNTARIO");
                Usuario u = new Usuario(nome, cpf, Integer.parseInt(idade), p);

                Voluntario v = new Voluntario(u, endereco);
                retorno = new VoluntarioDAO().cadastrar(v);
                req.setAttribute("retorno", retorno);
            }
            else if(opcao.equals("DEL")){
                String id = req.getParameter("id");
                retorno = new VoluntarioDAO().excluir(id);
                req.setAttribute("retorno", retorno);
                System.out.println(retorno);
            }
        }
        req.setAttribute("voluntarios", new VoluntarioDAO().getVoluntarios());
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/voluntarios.jsp");
        rd.forward(req, resp);
    }
}
