package de.tu_clausthal.in.mec.modeling.model.graph.jung;

import de.tu_clausthal.in.mec.modeling.model.graph.IEdge;
import de.tu_clausthal.in.mec.modeling.model.graph.INode;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.umd.cs.findbugs.annotations.NonNull;



/**
 * undirected graph
 *
 * @tparam N node type
 * @tparam E edge type
 */
public final class CUndirectedGraph<N extends INode, E extends IEdge> extends IBaseGraph<N, E>
{

    /**
     * ctor
     *
     * @param p_name name / identifier
     */
    public CUndirectedGraph( @NonNull final String p_name )
    {
        super( p_name, new UndirectedSparseGraph<>() );
    }

}
