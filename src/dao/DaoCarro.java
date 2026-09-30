package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Carro;
import model.Pessoa;


public class DaoCarro {
    
   
    Connection con = null;
    PreparedStatement pstm = null;

public List<Carro> getCarros()
{
    List<Carro> lista = new ArrayList<>();
    ResultSet rs = null;
    con = new Conexao().conectaBanco();
    
    try{
  
    pstm = con.prepareStatement("SELECT * FROM tb_carro", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    
    rs =  this.pstm.executeQuery();
    if(rs.first())
    {
        do{
             Carro c = new Carro();
             c.setId(rs.getInt("id"));
             c.setModelo(rs.getString("modelo"));
             c.setMarca(rs.getString("marca"));
             c.setAno(rs.getInt("ano"));
             
             
             lista.add(c);
            
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
      
    
public void salvarCarro(Carro car)
{
    con = new Conexao().conectaBanco();
    
    try{
    pstm = con.prepareStatement("INSERT INTO tb_carro (modelo,marca,ano) VALUES (?,?,?)");
    pstm.setString(1,car.getModelo());
    pstm.setString(2,car.getMarca());
    pstm.setInt(3, car.getAno());
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

public void editarCarro(Carro c)
{
    con = new Conexao().conectaBanco();
    
    try{
    pstm = con.prepareStatement("UPDATE tb_carro SET Modelo=?, Marca=?, Ano=? WHERE Id=?");
  
    pstm.setString(1,c.getModelo());
    pstm.setString(2,c.getMarca());
    pstm.setInt(3,c.getAno());
    pstm.setInt(4, c.getId());
    this.pstm.execute();
    
    
    pstm.close();
    }
    catch(SQLException erro)
    {
        JOptionPane.showMessageDialog(null, "Erro ao editar pessoa no BD "+erro);
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



public void exlcuirCarro(int id)
{
    con = new Conexao().conectaBanco();
    
    try{
    pstm = con.prepareStatement("DELETE FROM carro  WHERE id=?");
    pstm.setInt(1,id);
   
    this.pstm.execute();
    
    
    pstm.close();
    }
    catch(SQLException erro)
    {
        JOptionPane.showMessageDialog(null, "Erro ao excluir carro no BD "+erro);
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
