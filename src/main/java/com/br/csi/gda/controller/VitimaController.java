package br.csi.controller;

import br.csi.dao.VitimaDAO;
import br.csi.model.Permissao;
import br.csi.model.Riscos;
import br.csi.model.Usuario;
import br.csi.model.Vitima;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("VitimaController")
public class VitimaController extends HttpServlet {
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
                String nutricao = req.getParameter("fome");
                String desidratado = req.getParameter("sede");
                String frio = req.getParameter("frio");
                String machucado = req.getParameter("machucado");

                Permissao p = new Permissao(4, "VITIMA");
                Usuario u = new Usuario(nome, cpf, Integer.parseInt(idade), p);
                Riscos r = new Riscos();
                if (nutricao != null) {
                    r.setNutricao(true);
                } else {
                    r.setNutricao(false);
                }
                if (desidratado != null) {
                    r.setDesidrat(true);
                } else {
                    r.setDesidrat(false);
                }
                if (frio != null) {
                    r.setFrio(true);
                } else {
                    r.setFrio(false);
                }
                if (machucado != null) {
                    r.setMachucado(true);
                } else {
                    r.setMachucado(false);
                }
                Vitima v = new Vitima(u, r, endereco, true);
                retorno = new VitimaDAO().cadastrar(v);
                req.setAttribute("retorno", retorno);
            }
            else if(opcao.equals("DEL")){
                String id = req.getParameter("id");
                System.out.println("Excluir: " + id);
                retorno = new VitimaDAO().excluir(id);
                req.setAttribute("retorno", retorno);
            }
        }
        req.setAttribute("vitimas", new VitimaDAO().getVitimas());
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/vitimas.jsp");
        rd.forward(req, resp);
    }
}
