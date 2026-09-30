package controller;

import dao.DaoPessoa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Pessoa;
import javax.swing.JFrame;




public class ControllerPessoa {
    
    private final DaoPessoa dao;
    private List<Pessoa> pessoas;
    private int indice;

    public ControllerPessoa() {
        dao = new DaoPessoa();
        pessoas =  new ArrayList<>();
        indice =0;
        carregarPessoas();
    }
    

    




    
    
public void inserirPessoa(Pessoa p, JFrame form){
    if(!p.getNome().equals("") && !p.getCpf().equals("") && p.getIdade()!= 0)
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

private void carregarPessoas()
{
    pessoas = dao.getPessoa();
    
    if (pessoas == null)
    {
        pessoas = new ArrayList<>();
    }
    if(pessoas.isEmpty())
    {
        indice = 0;
    } else if(indice >= pessoas.size())
    {
        indice = pessoas.size()-1;
    }
        
}

 private boolean pessoaValida(Pessoa p)
{
  if (pessoas == null)
  {
      return false;
  }  
  if (p.getNome() == null || p.getNome().trim().isEmpty())
  {
      return false;
  }
  if (p.getCpf() == null || p.getCpf().trim().isEmpty())
  {
      return false;
  }
    if (p.getIdade() >= 0)
  {
      return false;
  }
  
  return true;

}
 



}