package de.tu_clausthal.in.mec.modeling.model.graph.jung;

import de.tu_clausthal.in.mec.modeling.model.graph.IEdge;
import de.tu_clausthal.in.mec.modeling.model.graph.IGraph;
import de.tu_clausthal.in.mec.modeling.model.graph.IGraphFactory;
import de.tu_clausthal.in.mec.modeling.model.graph.INode;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.text.MessageFormat;
import java.util.function.Function;


/**
 * factory to create graphs
 */
public enum EFactory implements IGraphFactory
{
    DIRECTEDGRAPH,
    UNDIRECTEDGRAPH,
    DIRECTEDMULTIGRAPH,
    UNDIRECTEDMULTIGRAPH;

    @Override
    public <N extends INode, E extends IEdge> IGraph<N, E> get( @NonNull final String p_name
    )
    {
        switch ( this )
        {
            case DIRECTEDGRAPH:
                return new CDirectedGraph<>( p_name );

            case UNDIRECTEDGRAPH:
                return new CUndirectedGraph<>( p_name );

            case DIRECTEDMULTIGRAPH:
                return new CDirectedMultiGraph<>( p_name );

            case UNDIRECTEDMULTIGRAPH:
                return new CUndirectedMultiGraph<>( p_name );

            default:
                throw new RuntimeException( MessageFormat.format( "graph type [{0}] unknown", this ) );
        }
    }
}
