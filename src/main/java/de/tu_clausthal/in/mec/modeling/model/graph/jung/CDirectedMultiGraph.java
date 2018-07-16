package de.tu_clausthal.in.mec.modeling.model.graph.jung;

import de.tu_clausthal.in.mec.modeling.model.graph.IEdge;
import de.tu_clausthal.in.mec.modeling.model.graph.INode;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.umd.cs.findbugs.annotations.NonNull;


public final class CDirectedMultiGraph<N extends INode, E extends IEdge> extends IBaseGraph<N, E>
{
    /**
     * ctor
     *
     * @param p_name identifier / name of the graph
     */
    public CDirectedMultiGraph( @NonNull final String p_name )
    {
        super( p_name, new DirectedSparseMultigraph<>() );
    }
}
