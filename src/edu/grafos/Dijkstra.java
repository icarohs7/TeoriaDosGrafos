import java.util.*;
import java.lang.*;
import java.io.*;
 
class CaminhoMinimo
{
    /*  A fun��o abaixo encontra o v�rtice com o menor valor de dist�ncia dos v�rtices
        n�o inclu�dos na �rvore de busca */
    static final int V = 20; /* altere de acordo com o n�mero de v�rtices do grafo */
    int distanciaMin(int dist[], Boolean visitados[])
    {
        /* Inicialize o menor valor */
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (visitados[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    /* A fun��o imprimeSolucao imprime a rota m�nima e o custo m�nimo
	    exemplo de sa�da:
        Caminho m�nimo = 0-2-3-5
        Custo m�nimo = 20		
	*/ 
    void imprimeSolucao(int dist[], int n)
    {
        // Fa�a o que se pede acima
      
    }
 
    /* A fun��o abaixo implementa o algoritmo de dijkstra para um grafo representado
	   por uma matriz de adjac�ncia */
    void dijkstra(int grafo[][], int src)
    {
        int dist[] = new int[V]; /* Vetor de dist�ncia m�nima*/
 
        /*Vetor de v�rtices visitados*/
        Boolean visitados[] = new Boolean[V];
 
        /* Inicializando todas as dist�ncia para infinito e marcando todos os v�rtices
		como n�o visitados */
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            visitados[i] = false;
        }
 
        /* Dist�ncia da origem � sempre 0 */
        dist[src] = 0;
 
        /* Encontra o menor caminho para todos os v�rtices */
        for (int count = 0; count < V-1; count++)
        {
            /* Pega a dist�ncia m�nima do v�rtice para o conjunto de v�rtives n�o visitados. u � sempre igual a src(origem) na primeira itera��o.*/
			
            int u = distanciaMin(dist, sptSet);
 
            /* Marca o v�rtice como visitado */
            visitados[u] = true;
 
			/* Atualiza a dist�ncia dos v�rtices adjacentes do v�rtice visitado*/
            for (int v = 0; v < V; v++)
 
     			/* Atualiza a dist[v] somente se o v�rtice n�o foi visitado e se 
				existir uma aresta de u para v e se o peso total do caminho de
				src(origem) para v onde u � o menor valor dos valores de dist[v] */
                if (!visitados[v] && grafo[u][v]!=-999 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+grafo[u][v] < dist[v])
                    dist[v] = dist[u] + grafo[u][v];
        }
 
        // Imprime o caminho m�nimo e o custo total m�nimo
        imprimeSolucao(dist, V);
    }
 
    public static void main (String[] args)
    {
       /*Preencha a matriz com as distancias do vertice atual para cada vertice
	   vizinho. Cada linha da matriz representa o vertice atual e cada coluna 
	   representa os vertices e a distancia dele para o vertice que a linha da matriz
	   representa. Os vertices das colunas com -999 representam que n�o s�o vizinhos do vertice que a linha da matriz representa */
       int grafo[][] = new int[][]{
           // 0   1   2   3   4   5   6   7   8 
           {  0,  0,  0,  0,  0,  0,  0,  0,},
           {  0,  0,  0,  0,  0,  0,  0,  0,},
           {  0,  0,  0,  0,  0,  0,  0,  0,},
           {  0,  0,  0,  0,  0,  0,  0,  0,},
           {  0,  0,  0,  0,  0,  0,  0,  0,},
           {  0,  0,  0,  0,  0,  0,  0,  0,},
           {  0,  0,  0,  0,  0,  0,  0,  0,},
           {  0,  0,  0,  0,  0,  0,  0,  0,},};
       
        CaminhoMinino t = new CaminhoMinino();
        t.dijkstra(grafo, 0);
    }
}