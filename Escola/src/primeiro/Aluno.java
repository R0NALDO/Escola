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
	
	public Aluno(String matricula) throws ClassNotFoundException, SQLException{
		
		Class.forName(			"com.mysql.jdbc.Driver");
		String 		stgCnxo = 	"jdbc:mysql://localhost/escola?autoReconnect=true&useSSL=false";
		Connection 	cnxo = DriverManager.getConnection(stgCnxo,"root", "");
		Statement 	cmdo = cnxo.createStatement();
		Scanner 	ler = new Scanner(System.in);
		String 		sql =     "SELECT N.NOTA, N.TERMO, D.CODIGO_DISCIPLINA, D.DISCIPLINA "
							+ "FROM NOTAS N INNER JOIN DISCIPLINA D ON N.CODIGO_DISCIPLINA = D.CODIGO_DISCIPLINA "
							+ "WHERE MATRICULA = "+matricula;

		ResultSet rstst = cmdo.executeQuery(sql);
		
		rstst.next(); //pula primeira linha, pois é nula
	
		Nota 		nota = new Nota();
		Disciplina 	disciplina = new Disciplina();
		
		disciplina.codigo = rstst.getString("CODIGO_DISCIPLINA");
		disciplina.nome = 	rstst.getString("DISCIPLINA");
		nota.disciplina = 	disciplina;
		nota.termo = 		rstst.getString("TERMO");
		nota.valor = 		rstst.getDouble("NOTA");
		notas.add(nota);
		
		this.matricula = matricula;
		this.nome = "ronaldo";
		this.email = "ron@gmail.com";
		
		cnxo.close();
	}
}
