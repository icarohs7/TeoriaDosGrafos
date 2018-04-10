import java.util.*;
import java.lang.*;
import java.io.*;
 
class CaminhoMinimo
{
    /*  A função abaixo encontra o vértice com o menor valor de distância dos vértices
        não incluídos na árvore de busca */
    static final int V = 20; /* altere de acordo com o número de vértices do grafo */
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
 
    /* A função imprimeSolucao imprime a rota mínima e o custo mínimo
	    exemplo de saída:
        Caminho mínimo = 0-2-3-5
        Custo mínimo = 20		
	*/ 
    void imprimeSolucao(int dist[], int n)
    {
        // Faça o que se pede acima
      
    }
 
    /* A função abaixo implementa o algoritmo de dijkstra para um grafo representado
	   por uma matriz de adjacência */
    void dijkstra(int grafo[][], int src)
    {
        int dist[] = new int[V]; /* Vetor de distância mínima*/
 
        /*Vetor de vértices visitados*/
        Boolean visitados[] = new Boolean[V];
 
        /* Inicializando todas as distância para infinito e marcando todos os vértices
		como não visitados */
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            visitados[i] = false;
        }
 
        /* Distância da origem é sempre 0 */
        dist[src] = 0;
 
        /* Encontra o menor caminho para todos os vértices */
        for (int count = 0; count < V-1; count++)
        {
            /* Pega a distância mínima do vértice para o conjunto de vértives não visitados. u é sempre igual a src(origem) na primeira iteração.*/
			
            int u = distanciaMin(dist, sptSet);
 
            /* Marca o vértice como visitado */
            visitados[u] = true;
 
			/* Atualiza a distância dos vértices adjacentes do vértice visitado*/
            for (int v = 0; v < V; v++)
 
     			/* Atualiza a dist[v] somente se o vértice não foi visitado e se 
				existir uma aresta de u para v e se o peso total do caminho de
				src(origem) para v onde u é o menor valor dos valores de dist[v] */
                if (!visitados[v] && grafo[u][v]!=-999 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+grafo[u][v] < dist[v])
                    dist[v] = dist[u] + grafo[u][v];
        }
 
        // Imprime o caminho mínimo e o custo total mínimo
        imprimeSolucao(dist, V);
    }
 
    public static void main (String[] args)
    {
       /*Preencha a matriz com as distancias do vertice atual para cada vertice
	   vizinho. Cada linha da matriz representa o vertice atual e cada coluna 
	   representa os vertices e a distancia dele para o vertice que a linha da matriz
	   representa. Os vertices das colunas com -999 representam que não são vizinhos do vertice que a linha da matriz representa */
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