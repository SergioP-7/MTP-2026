package controller;

import dao.DaoPessoa;
import java.util.ArrayList;
import java.util.List;
import model.Pessoa;





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
    


    
public boolean inserirPessoa(Pessoa p){
    if (!pessoaValida(p))
    {
     return false;   
    }    
dao.salvarPessoa(p);

carregarPessoas();

ultimo();

return true;

}

public boolean alterarPessoa(Pessoa p){
    if (!pessoaValida(p))
    {
     return false;   
    }
    if (p.getId()<=0)
    {
        return false;
    }
   dao.editarPessoa(p);

carregarPessoas();

return true;

}




public Pessoa deletarPessoa(int id) 
{
    if (id <= 0)
    {
        return null;  
    }
   Pessoa p = getPessoaporId(indice);
   
   int indiceExcluido = indice;
   dao.excluirPessoa(id);
   carregarPessoas();
   
   if(pessoas.isEmpty())
   {
       indice = 0;
       return null;
   }
   
   
   
   if (indiceExcluido< pessoas.size())
   {
       indice = indiceExcluido;
   }
   else {
       indice = pessoas.size()-1;
   }
return pessoas.get(id);

   
}

public List<Pessoa> getPessoa()
{
    carregarPessoas();
    return pessoas;
}

public Pessoa getPessoaporId(int id)
{
   for (Pessoa p : pessoas)
   {
    if (p.getId()== id)
    {
        return p;
    }    
   }
   return null;
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
 
public Pessoa ultimo()
{   
  if(pessoas.isEmpty())
  {
      return null;
  }  
  indice = pessoas.size()-1;
  return pessoas.get(indice);
  
}

public Pessoa primeiro()
{
    if(pessoas.isEmpty())
    { 
      return null;
    }
    indice = 0;
    return pessoas.get(indice);
}

public Pessoa proximo()
{
    if(pessoas.isEmpty())
    { 
      return null;
    }
    if (indice < pessoas.size()-1)
    {
        indice ++;
    }
    return pessoas.get(indice);
        
}

    public Pessoa anterior()
{
    if(pessoas.isEmpty())
    { 
      return null;
    }
    if (indice > 0)
    {
        indice --;
    }
    return pessoas.get(indice);

}
    

    public int getIndice()
{
    return indice;
}

public int getQuantidadePessoas()
{
    return pessoas.size();
}

// Controle de Navegação//

public boolean temPessoas()
{
  return !pessoas.isEmpty();
}

public boolean temAnterior()
{
    return !pessoas.isEmpty() && indice > 0;
}

public boolean temProximo()
{
     return !pessoas.isEmpty() && indice < pessoas.size()-1;
}



    
}