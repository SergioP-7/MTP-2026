package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pessoa;


public class DaoPessoa{


    Connection con = null;
    PreparedStatement pstm = null;

    
public List<Pessoa> getPessoa()
{
    List<Pessoa> lista = new ArrayList<>();
    ResultSet rs = null;
    con = new Conexao().conectaBanco();
    
    try{
  
    pstm = con.prepareStatement("SELECT * FROM tb_pessoa", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    
    rs =  this.pstm.executeQuery();
    if(rs.first())
    {
        do{
             Pessoa p = new Pessoa();
             p.setId(rs.getInt("Id"));
             p.setCpf(rs.getString("CPF"));
             p.setIdade(rs.getInt("Idade"));
             p.setNome(rs.getString("Nome"));
              
             lista.add(p);
            
        }while(rs.next());
    }
    
    pstm.close();
  
    }   
    catch(SQLException e)
    {
        throw new RuntimeException ("Erro ao buscar dados no BD ", e);
    }
    
    
    finally{
        try{
        con.close();
        }
        catch(SQLException e)
        {
            throw new RuntimeException("Erro ao fechar a conexão de busca ", e);
        }
    }
    
    return lista;
}



public void salvarPessoa(Pessoa p)
{
    con = new Conexao().conectaBanco();
    
    try{
    pstm = con.prepareStatement("INSERT INTO tb_pessoa (CPF,Nome,Idade) VALUES (?,?,?)");
    pstm.setString(1,p.getCpf());
    pstm.setString(2,p.getNome());
    pstm.setInt(3,p.getIdade());
    this.pstm.execute();
    
    
    pstm.close();
    }
    catch(SQLException e)
    {
        throw new RuntimeException ("Erro ao salvar pessoa no BD ", e);
    }
    finally{
        try{
        con.close();
        }
        catch(SQLException e)
        {
            throw new RuntimeException ("Erro ao fechar a conexão de salvamento ", e);
        }
    }
    
}


public void editarPessoa(Pessoa p)
{
    con = new Conexao().conectaBanco();
    
    try{
    pstm = con.prepareStatement("UPDATE tb_pessoa SET Nome=?, Cpf=?, Idade=? WHERE Id=?");
  
    pstm.setString(1,p.getNome());
    pstm.setString(2,p.getCpf());
    pstm.setInt(3,p.getIdade());
    pstm.setInt(4, p.getId());
    this.pstm.execute();
    
    
    pstm.close();
    }
    catch(SQLException e)
    {
        throw new RuntimeException ("Erro ao editar pessoa no BD ", e);
    }
    finally{
        try{
        con.close();
        }
        catch(SQLException e)
        {
            throw new RuntimeException ("Erro ao fechar a conexão de edição ", e);
        }
    }
    
}

public void excluirPessoa(int Id)
{
    con = new Conexao().conectaBanco();
    
    try{
    pstm = con.prepareStatement("DELETE FROM tb_pessoa  WHERE Id=?");
    pstm.setInt(1,Id);
   
    this.pstm.execute();
    
    
    pstm.close();
    }
    catch(SQLException e)
    {
            throw new RuntimeException ("Erro ao excluir pessoa no BD ", e);
    }
    finally{
        try{
        con.close();
        }
        catch(SQLException e)
        {
            throw new RuntimeException ("Erro ao fechar a conexão de salvamento ", e);
        }
    }
    

}



}