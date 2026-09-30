
package controller;

import conexao.Conexao;
import dao.DaoCarro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Carro;

public class ControllerCarro {
    dao.DaoCarro dao = new dao.DaoCarro();
    
    
public void inserirPessoa(Carro c, JFrame form){
    if(!c.getModelo().equals("") && !c.getMarca().equals("") && c.getId()!= 0)
    {
        dao.salvarPessoa(p);
        JOptionPane.showMessageDialog(form, "Pessoa salva com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE );
    }
    else{
        JOptionPane.showMessageDialog(form, "Existem campos em branco!! ", "Alerta", JOptionPane.WARNING_MESSAGE);
    }
}
    public void editarPessoa(Pessoa p, JFrame form){
    if(!p.getNome().equals("") && !p.getCpf().equals("") && p.getIdade()!= 0 && p.getId()!=0)
    {
        dao.editarPessoa(p);
        JOptionPane.showMessageDialog(form, "Pessoa alterada com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE );
    }
    else{
        JOptionPane.showMessageDialog(form, "Existem campos em branco!! ", "Alerta", JOptionPane.WARNING_MESSAGE);
    }
}


public void deletarPessoa(int identificador, JFrame form) 
{
    dao.excluirPessoa(identificador);
    JOptionPane.showMessageDialog(form, "Pessoa excluída com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE );
}

public List<Pessoa> getPessoa()
{
    List<Pessoa> pessoas = new ArrayList<>();
    pessoas.clear();
    pessoas= dao.getPessoa();
    return pessoas;
}
}

    
}
