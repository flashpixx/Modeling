package de.tu_clausthal.in.mec.modeling.model.graph.jung;

import de.tu_clausthal.in.mec.modeling.model.graph.IEdge;
import de.tu_clausthal.in.mec.modeling.model.graph.INode;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.umd.cs.findbugs.annotations.NonNull;


/**
 * undirected multi graph
 *
 * @tparam N node type
 * @tparam E edge type
 */
public final class CUndirectedMultiGraph<N extends INode, E extends IEdge> extends IBaseGraph<N, E>
{
    /**
     * ctor
     *
     * @param p_name identifier / name of the graph
     */
    public CUndirectedMultiGraph( @NonNull final String p_name )
    {
        super( p_name, new UndirectedSparseMultigraph<>() );
    }
}
