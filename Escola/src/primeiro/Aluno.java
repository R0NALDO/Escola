package primeiro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Aluno {
	
	public String matricula;
	public String nome;
	public String email;
	public ArrayList<Nota> notas = new ArrayList<Nota>();
	
	public Aluno(String nomex) throws ClassNotFoundException, SQLException{
		
		Class.forName(			"com.mysql.jdbc.Driver");
		String 		stgCnxo = 	"jdbc:mysql://localhost/escola?autoReconnect=true&useSSL=false";
		Connection 	cnxo = DriverManager.getConnection(stgCnxo,"root", "");
		Statement 	cmdo = cnxo.createStatement();
//		Scanner 	ler = new Scanner(System.in);
		
//		String 		sql =     "SELECT N.NOTA, N.TERMO, D.CODIGO_DISCIPLINA, D.DISCIPLINA FROM NOTAS N INNER JOIN DISCIPLINA D ON N.CODIGO_DISCIPLINA = D.CODIGO_DISCIPLINA WHERE MATRICULA = "+nomex;
		String 		sqlAll = "SELECT N.NOTA, N.TERMO, D.CODIGO_DISCIPLINA, D.DISCIPLINA, A.NOME, A.MATRICULA, A.EMAIL FROM NOTAS N LEFT JOIN DISCIPLINA D ON N.CODIGO_DISCIPLINA =  D.CODIGO_DISCIPLINA LEFT JOIN ALUNO A ON A.MATRICULA = N.MATRICULA";

		ResultSet 	rstst = cmdo.executeQuery(sqlAll);
	
		while (rstst.next()) {
			Nota 		nota = new Nota();
			Disciplina 	disciplina = new Disciplina();
			
			disciplina.codigo = rstst.getString("CODIGO_DISCIPLINA");
			disciplina.nome = 	rstst.getString("DISCIPLINA");
			nota.disciplina = 	disciplina;
			nota.termo = 		rstst.getString("TERMO");
			nota.valor = 		rstst.getDouble("NOTA");
			
			notas.add(nota);
			
			this.matricula = 	rstst.getString("MATRICULA");
			this.nome = 		rstst.getString("NOME");
			this.email = 		rstst.getString("EMAIL");
			
			if (nomex.equals(this.nome)) {
				System.out.println(this.nome + " " + this.email + " " + this.matricula + "\n " + nota.valor + " "
						+ nota.termo + " " + disciplina.codigo + " " + disciplina.nome);
			}
		}
		cnxo.close();
	}
}
