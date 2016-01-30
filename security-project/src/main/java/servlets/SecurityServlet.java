package servlets;

import java.io.IOException;

import javax.annotation.security.DeclareRoles;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A anotacao ServletSecurity serve para especificar configuracoes de seguranca
 * programaticamente para uma servlet especifica. Essa anotacao possui 2
 * atributos, um value que recebe um HttpConstrant e define constraints de
 * seguranca para todos os metodos que nao foram especificados pelo segundo
 * atributo, httpMethodConstraints, que por sua vez, define constraints de
 * seguranca para determinados metodos HTTP.
 * <hr>
 * A anotacao HttpConstrant define uma constraint de seguranca para todos os
 * metodos HTTP que nao foram especificados em httpMethodConstraints de
 * ServletSecurity. O value dessa anotacao aponta para a enum
 * ServletSecurity.EmptyRoleSemantic que possui 2 constantes: PERMIT (default) e
 * DENY. EmptyRoleSemantic define a acao padrao de seguranca caso nenhuma role
 * seja especificada no array de roles permitidas do atributo rolesAlowed. O
 * atributo rolesAlowed, por sua vez, define as roles que possuem permissao para
 * acessar essa servlet. Por fim, o atributo transportGarantee, define o tipo de
 * seguranca aplicada no transporte dos dados do cliente para o servidor. Os
 * valores possiveis sao NONE (default) e CONFIDENTIAL (relacionado com HTTPS,
 * ou seja, exige que os dados sejam trafegados atraves de uma conexao HTTPS).
 * <hr>
 * O atributo httpMethodContraints de ServletSecurity indica configuracoes de
 * seguranca para metodos HTTP serpecificos. Ele aceita um valor do tipo
 * HttpMethodConstraint que possui 4 atributos: value (metodo HTTP que esta
 * restricao se aplicara, obrigatorio), emptyRoleSemantic (semelhante ao
 * atributo value de HttpConstrant, define a acao padrao de seguranca caso o
 * array de roles permitidas esteja vazio, o valor padrao é
 * ServletSecurity.EmptyRoleSemantic.PERMIT), rolesAlowed (array de roles que
 * possuem acesso a essa servlet) e transportGarantee (define o tipo de
 * seguranca aplicada no transporte dos dados do cliente para o servidor. Possui
 * o valor padrao NONE, porem aceita o valor CONFIDENTIAL, que exige que os
 * dados sejam transportados atraves de uma conexao HTTPS segura).
 * <hr>
 * A anotacao DeclareRoles é analoga ao elemento security-role do web.xml e
 * define uma, ou mais, roles da aplicacao programaticamente. Quando o container
 * é inicializado, ele le todas as configuracoes do web.xml e depois as
 * configuracoes das anotacoes, entao ele registra as roles definidas em
 * DeclareRoles para serem utilizadas para a aplicacao.
 * 
 * @author Matheus
 *
 */
@DeclareRoles({ "admin", "role2" })
@ServletSecurity(value = @HttpConstraint(value = EmptyRoleSemantic.PERMIT, rolesAllowed = { "admin" }, transportGuarantee = TransportGuarantee.CONFIDENTIAL),
		httpMethodConstraints = { @HttpMethodConstraint(value = "POST", emptyRoleSemantic = EmptyRoleSemantic.PERMIT, rolesAllowed = "root",
				transportGuarantee = TransportGuarantee.NONE) })
@WebServlet(urlPatterns = "/SecurityServlet", asyncSupported = true, name = "SecurityServlet", loadOnStartup = 2)
public class SecurityServlet extends HttpServlet {

	private static final long serialVersionUID = -2287750936104197612L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("doGet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("doPost");
	}

}
