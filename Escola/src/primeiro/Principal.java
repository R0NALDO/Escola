package primeiro;

import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
	Aluno fulano = new Aluno("123");
	
	System.out.println(	fulano.matricula+"\n"+
						fulano.nome+"\n"+
						fulano.email);
	
		for(Nota nt : fulano.notas){
			System.out.println(	 	 nt.termo+" - "
									+nt.valor+" - "
									+nt.disciplina.codigo+" - "
									+nt.disciplina.nome+" - ");
		}
	}
}
