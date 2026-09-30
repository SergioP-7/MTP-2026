package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Pessoa;
import model.Produto;


public class DaoProduto{


    Connection con = null;
    PreparedStatement pstm = null;

    
public List<Produto> getProduto()
{
    List<Produto> lista = new ArrayList<>();
    ResultSet rs = null;
    con = new Conexao().conectaBanco();
    
    try{
  
    pstm = con.prepareStatement("SELECT * FROM tb_produto", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    
    rs =  this.pstm.executeQuery();
    if(rs.first())
    {
        do{
             Produto p = new Produto();
             p.setId(rs.getInt("Id"));
             p.setDescricao(rs.getString("Descrição"));
             p.setQuantidade(rs.getInt("Quantidade"));
             p.setValor(rs.getInt("valor"));
              
             lista.add(p);
            
        }while(rs.next());
    }
    
    pstm.close();
  
    }   
    catch(SQLException erro)
    {
        JOptionPane.showMessageDialog(null, "Erro ao buscar dados no BD "+erro);
    }
    
    
    finally{
        try{
        con.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de busca "+err);
        }
    }
    
    return lista;
}



public void salvarProduto(Produto p)
{
    con = new Conexao().conectaBanco();
    
    try{
    pstm = con.prepareStatement("INSERT INTO tb_produto (Descrição, Quantidade, valor) VALUES (?,?,?)");
    pstm.setString(1,p.getDescricao());
    pstm.setInt(2,p.getQuantidade());
    pstm.setDouble(3,p.getValor());
    this.pstm.execute();
    
    
    pstm.close();
    }
    catch(SQLException erro)
    {
        JOptionPane.showMessageDialog(null, "Erro ao salvar carro no BD "+erro);
    }
    finally{
        try{
        con.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de salvamento "+err);
        }
    }
    
}

public void editarProduto(Produto p)
{
    con = new Conexao().conectaBanco();
    
    try{
    pstm = con.prepareStatement("UPDATE tb_produto SET Descricao=?, Quantidade=?, Valor=? WHERE Id=?");
  
    pstm.setString(1,p.getDescricao());
    pstm.setInt(2,p.getQuantidade());
    pstm.setDouble(3,p.getValor());
    pstm.setInt(4, p.getId());
    this.pstm.execute();
    
    
    pstm.close();
    }
    catch(SQLException erro)
    {
        JOptionPane.showMessageDialog(null, "Erro ao editar produto no BD "+erro);
    }
    finally{
        try{
        con.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de edição "+err);
        }
    }
    
}

public void excluirProduto(String descricao)
{
    con = new Conexao().conectaBanco();
    
    try{
    pstm = con.prepareStatement("DELETE FROM tb_produto  WHERE Descrição=?");
    pstm.setString(1,descricao);
   
    this.pstm.execute();
    
    
    pstm.close();
    }
    catch(SQLException erro)
    {
        JOptionPane.showMessageDialog(null, "Erro ao excluir produto no BD "+erro);
    }
    finally{
        try{
        con.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de salvamento "+err);
        }
    }
    

}



}
