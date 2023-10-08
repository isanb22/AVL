package ArvoreAVL;

public class AVL {

    private Node raiz;

    public AVL() 
    {
        this.raiz = null;

    }

    public Node getRaiz() 
    {
        return raiz;
    }

    /* -------------- Adicionar Node na arvore -------------- */
    public void adicionar(int info) 
    {
    	Node novoNode = new Node();
    	novoNode.setInfo(info);

        // Checa se existe uma raiz
        if (raiz == null) 
        {
            raiz = novoNode;
            System.out.println("ADICIONADO!");
            return;
        } 
        
        Node atual = raiz;

        while (true) 
        {
            
            if (novoNode.getInfo() < atual.getInfo()) 
            {
                if (atual.getEsquerda() != null) {
                    atual = atual.getEsquerda();
                }
                
                else {
                    atual.setEsquerda(novoNode);
                    atual.setAltura(-1);
                    break;
                }

            }
            
            else 
            {
                if (atual.getDireita() != null) {
                    atual = atual.getDireita();
                }
                
                else {
                    atual.setDireita(novoNode);
                    atual.setAltura(1);
                    break;
                }
            }
        }
        
        novoNode.setAnterior(atual);
        
        int balanco = atual.getAltura();
        Node temp;
        
        // Rotação esquerda:
        if (balanco > 1)
        {
        	if (atual.getEsquerda() == null)
        	{
        		temp = atual.getAnterior();
        		atual.setEsquerda(temp);
        		atual.setAnterior(temp.getAnterior());
        		temp.setAnterior(atual);
        		
        		temp.setEsquerda(null);
        	}
        	else
        	{
        		Node tempEsquerda = atual.getEsquerda();

        		temp = atual.getAnterior();
        		atual.setEsquerda(temp);
        		atual.setAnterior(temp.getAnterior());
        		temp.setAnterior(atual);
        		
        		temp.setEsquerda(tempEsquerda);
        		tempEsquerda.setAnterior(temp);
        		
        	}
        }
        // Rotacao direita:
        if (balanco < -1)
        {
        	if (atual.getDireita() == null)
        	{
        		temp = atual.getAnterior();
        		atual.setDireita(temp);
        		atual.setAnterior(temp.getAnterior());
        		temp.setAnterior(atual);
        		temp.setDireita(null);
        	}
        	else
        	{
        		Node tempDireita = atual.getDireita();

        		temp = atual.getAnterior();
        		atual.setDireita(temp);
        		atual.setAnterior(temp.getAnterior());
        		temp.setAnterior(atual);
        		
        		temp.setEsquerda(tempDireita);
        		tempDireita.setAnterior(temp);
        		
        	}
        }
        
        if (atual.getAnterior() == null) { raiz = atual; }


        System.out.println("ADICIONADO!");

    }
    /* -------------- Remover Node da arvore -------------- */
    public void remover(int info) 
    {
        Node atual = null;
        Node pai = null;

        atual = busca(info);

        if (atual == null) 
        {
            return;
        } // Se não encontrar

        if (atual != raiz) 
        {
            pai = atual.getAnterior();
        } // Se não for a raíz


        // Se tiver filhos a direita:
        if (atual.getDireita() != null) 
        {
            Node temp = atual.getDireita();

            while (temp.getEsquerda() != null) 
            {
                temp = temp.getEsquerda();
            }


            if (pai != null) 
            {
                // Se o pai do temporario for o node que vamos deletar:
                if (atual == temp.getAnterior()) 
                {
                    // Se o node (para deletar) for o da direita:
                    if (pai.getDireita() == atual) 
                    {
                        pai.setDireita(temp);
                        temp.setEsquerda(atual.getEsquerda());
                    }
                    // Se for o node da esquerda
                    else 
                    {
                        pai.setEsquerda(temp);
                        temp.setEsquerda(atual.getEsquerda());
                    }
                    temp.setAnterior(pai);
                }

                // Remove 11
                else 
                {
                    atual.setInfo(temp.getInfo());
                    Node tempPai = temp.getAnterior();

                    if (temp.getDireita() == null) 
                    {
                        tempPai.setEsquerda(null);
                    } 
                    
                    else 
                    {
                        tempPai.setEsquerda(temp.getDireita());
                        temp.getDireita().setAnterior(tempPai);
                    }

                }
            } 
            else 
            {
                // Se o pai do temporario for o node que vamos deletar:
                if (atual == temp.getAnterior()) 
                {
                    temp.setEsquerda(atual.getEsquerda());
                    temp.setAnterior(null);
                    atual.getEsquerda().setAnterior(temp);

                    raiz = temp;
                } 
                
                else
                {
                    atual.setInfo(temp.getInfo());
                    Node tempPai = temp.getAnterior();

                    if (temp.getDireita() == null) 
                    {
                        tempPai.setEsquerda(null);
                    } 
                    
                    else 
                    {
                        tempPai.setEsquerda(temp.getDireita());
                        temp.getDireita().setAnterior(tempPai);
                    }
                }
            }
        }

        // Se tiver filhos somente a esquerda:
        else if (atual.getEsquerda() != null) 
        {
            Node temp = atual.getEsquerda();

            while (temp.getDireita() != null) 
            {
                temp = temp.getDireita();
            }

            if (pai != null) 
            {
                if (atual == temp.getAnterior()) 
                {
                    if (pai.getDireita() == atual) 
                    {
                        pai.setDireita(temp);
                    } 
                    
                    else 
                    {
                        pai.setEsquerda(temp);
                    }
                    temp.setAnterior(pai);
                } 
                else 
                {
                    atual.setInfo(temp.getInfo());
                    Node tempPai = temp.getAnterior();

                    // Se o node (para deletar) for o da direita:
                    if (tempPai.getDireita() == temp) 
                    {
                        tempPai.setDireita(null);
                    }
                    // Se for o node da esquerda
                    else 
                    {
                        tempPai.setEsquerda(null);
                    }
                }
            } 
            else 
            {
                // Se o pai do temporario for o node que vamos deletar:
                if (atual == temp.getAnterior()) 
                {
                    temp.setAnterior(null);

                    raiz = temp;
                } 
                
                else 
                {
                    atual.setInfo(temp.getInfo());
                    Node tempPai = temp.getAnterior();

                    if (temp.getEsquerda() == null) 
                    {
                        tempPai.setDireita(null);
                    } 
                    else 
                    {
                        temp.getEsquerda().setAnterior(tempPai);
                    }
                }
            }
        }

        // Se não tiver filhos (ta certo)
        else 
        {
            if (pai != null) 
            {
                // Se o node (para deletar) for o da direita:
                if (pai.getDireita() == atual) 
                {
                    pai.setDireita(null);
                }
                // Se for o node da esquerda
                else 
                {
                    pai.setEsquerda(null);
                }
            }
        }

        System.out.println("Node deletado");

    }

    /* --------------  Busca Node na arvore -------------- */
    public Node busca(int info) 
    {
        Node atual = raiz;
        int count = 0;

        while (atual != null) 
        {
            count++;

            if (atual.getInfo() == info) 
            {
                System.out.println("Achou em " + count + " galhos percorridos");
                return atual;
            } 
            
            else 
            {
                if (atual.getInfo() > info) 
                {
                    atual = atual.getEsquerda();
                } 
                
                else 
                {
                    atual = atual.getDireita();
                }
            }
        }

        System.out.println("Nao foi possivel achar esse node! " + count + " galhos percorridos");
        return null;

    }

    /* -------------- Métodos para printar -------------- */
    // Printar Em-Ordem:
    public void print_EmOrdem(Node atual) {
        if (atual != null) {
            print_EmOrdem(atual.getEsquerda());
            atual.printInfo();
            print_EmOrdem(atual.getDireita());
        }
    }

    public void print_PreOrdem(Node atual) {
        if (atual != null) {
            atual.printInfo();
            print_PreOrdem(atual.getEsquerda());
            print_PreOrdem(atual.getDireita());
        }
    }

    public void print_PosOrdem(Node atual) {
        if (atual != null) {
            print_PosOrdem(atual.getEsquerda());
            print_PosOrdem(atual.getDireita());
            atual.printInfo();
        }
    }
}

