/*
 * @cond LICENSE
 * ######################################################################################
 * # LGPL License                                                                       #
 * #                                                                                    #
 * # This file is part of the TU-Clausthal Mobile and Enterprise Computing Modeling     #
 * # Copyright (c) 2018-19                                                              #
 * # This program is free software: you can redistribute it and/or modify               #
 * # it under the terms of the GNU Lesser General Public License as                     #
 * # published by the Free Software Foundation, either version 3 of the                 #
 * # License, or (at your option) any later version.                                    #
 * #                                                                                    #
 * # This program is distributed in the hope that it will be useful,                    #
 * # but WITHOUT ANY WARRANTY; without even the implied warranty of                     #
 * # MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                      #
 * # GNU Lesser General Public License for more details.                                #
 * #                                                                                    #
 * # You should have received a copy of the GNU Lesser General Public License           #
 * # along with this program. If not, see http://www.gnu.org/licenses/                  #
 * ######################################################################################
 * @endcond
 */

package de.tu_clausthal.in.mec.modeling.model.graph.jung;

import de.tu_clausthal.in.mec.modeling.model.graph.IEdge;
import de.tu_clausthal.in.mec.modeling.model.graph.IGraph;
import de.tu_clausthal.in.mec.modeling.model.graph.INode;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.algorithms.shortestpath.PrimMinimumSpanningTree;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.Graph;
import edu.umd.cs.findbugs.annotations.NonNull;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;


/**
 * abstract graph class
 *
 * @tparam N node type
 * @tparam E edge type
 */
public abstract class IBaseGraph<N extends INode, E extends IEdge> implements IGraph<N, E>
{
    /**
     * graph instance
     */
    protected final Graph<N, E> m_graph;
    /**
     * map with nodes
     */
    protected final Map<String, N> m_nodes = new ConcurrentHashMap<>();
    /**
     * map with edges
     */
    protected final Map<String, E> m_edges = new ConcurrentHashMap<>();
    /**
     * id of the graph
     */
    private final String m_id;
    /**
     * shortest path algorithm
     */
    private final DijkstraShortestPath<N, E> m_shortestpath;

    /**
     * ctor
     *
     * @param p_id identifier / name of the graph
     * @param p_graph graph instance
     */
    protected IBaseGraph( @NonNull final String p_id, @NonNull final Graph<N, E> p_graph )
    {
        m_id = p_id;
        m_graph = p_graph;
        m_shortestpath = new DijkstraShortestPath<>( m_graph, i -> i.get().doubleValue() );
    }

    @Override
    public final int hashCode()
    {
        return m_id.hashCode();
    }

    @Override
    public final boolean equals( final Object p_object )
    {
        return p_object instanceof IGraph<?, ?> && p_object.hashCode() == this.hashCode();
    }

    @NonNull
    @Override
    public final String id()
    {
        return m_id;
    }

    @NonNull
    @Override
    public final Stream<? extends E> edges()
    {
        return m_graph.getEdges().stream();
    }

    @NonNull
    @Override
    public final Stream<? extends N> nodes()
    {
        return m_graph.getVertices().stream();
    }

    @Override
    public boolean containsnode( @NonNull final String p_id )
    {
        return m_nodes.containsKey( p_id );
    }

    @Override
    public boolean containsedge( @NonNull final String p_id )
    {
        return m_edges.containsKey( p_id );
    }

    @Override
    public final E edge( @NonNull final String p_id )
    {
        return m_edges.get( p_id );
    }

    @Override
    public final N node( @NonNull final String p_id )
    {
        return m_nodes.get( p_id );
    }

    @Override
    public final Stream<? extends E> shortestpath( @Nonnull final String p_start, @Nonnull final String p_end )
    {
        return m_shortestpath.getPath( Objects.requireNonNull( m_nodes.get( p_start ) ), Objects.requireNonNull( m_nodes.get( p_end ) ) ).stream();
    }

    @Override
    public final IGraph<N, E> spanningtree( @Nonnull final String p_id )
    {
        return new CSpanningTree<>( p_id, Objects
            .requireNonNull( new PrimMinimumSpanningTree<N, E>( DelegateTree.getFactory(), i -> i.get().doubleValue() ).apply( m_graph ) ) );
    }

    @Override
    public Stream<? extends E> inedges( @NonNull final Stream<N> p_nodes )
    {
        return p_nodes.flatMap( i -> m_graph.getInEdges( i ).stream() );
    }

    @Override
    public Stream<? extends E> outedges( @NonNull final Stream<N> p_nodes )
    {
        return p_nodes.flatMap( i -> m_graph.getOutEdges( i ).stream() );
    }

    @Override
    public Stream<? extends N> neighbours( @NonNull final Stream<N> p_nodes )
    {
        return p_nodes.flatMap( i -> m_graph.getNeighbors( i ).stream() );
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public final Stream<? extends E> inedges( @NonNull final N... p_nodes )
    {
        return this.inedges( Arrays.stream( p_nodes ) );
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public final Stream<? extends E> outedges( @NonNull final N... p_nodes )
    {
        return this.outedges( Arrays.stream( p_nodes ) );
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public final Stream<? extends N> neighbours( @NonNull final N... p_nodes )
    {
        return this.neighbours( Arrays.stream( p_nodes ) );
    }

    @Override
    public final Stream<? extends E> inedges( @NonNull final String... p_nodes )
    {
        return this.inedges( Arrays.stream( p_nodes ).map( m_nodes::get ).filter( Objects::nonNull ) );
    }

    @Override
    public final Stream<? extends E> outedges( @NonNull final String... p_nodes )
    {
        return this.outedges( Arrays.stream( p_nodes ).map( m_nodes::get ).filter( Objects::nonNull ) );
    }

    @Override
    public Stream<? extends N> neighbours( @NonNull final String... p_nodes )
    {
        return this.neighbours( Arrays.stream( p_nodes ).map( m_nodes::get ).filter( Objects::nonNull ) );
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public IGraph<N, E> removeedge( @NonNull final Stream<E> p_edges )
    {
        p_edges.peek( i -> m_edges.remove( i.id() ) ).forEach( m_graph::removeEdge );
        return this;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public IGraph<N, E> removeedge( @NonNull final E... p_edges )
    {
        return this.removeedge( Arrays.stream( p_edges ) );
    }

    @Override
    public IGraph<N, E> removeedge( @NonNull final String... p_edges )
    {
        return this.removeedge( Arrays.stream( p_edges ).map( m_edges::get ).filter( Objects::nonNull ) );
    }

    @Override
    public IGraph<N, E> removenode( @NonNull final Stream<N> p_nodes )
    {
        p_nodes.peek( i -> m_nodes.remove( i.id() ) ).forEach( m_graph::removeVertex );
        return this;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public IGraph<N, E> removenode( @NonNull final N... p_nodes )
    {
        return this.removenode( Arrays.stream( p_nodes ) );
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public IGraph<N, E> removenode( @NonNull final String... p_nodes )
    {
        return this.removenode( Arrays.stream( p_nodes ).map( m_nodes::get ).filter( Objects::nonNull ) );
    }

    @Override
    public IGraph<N, E> addedge( @NonNull final N p_start, @NonNull final N p_end, @NonNull final E p_edge )
    {
        m_graph.addEdge( m_edges.putIfAbsent( p_edge.id(), p_edge ), m_nodes.putIfAbsent( p_start.id(), p_start ), m_nodes.putIfAbsent( p_end.id(), p_end ) );
        return this;
    }

    @Override
    public IGraph<N, E> addnode( @NonNull final Stream<N> p_nodes )
    {
        p_nodes.peek( i -> m_nodes.put( i.id(), i ) ).forEach( m_graph::addVertex );
        return this;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public IGraph<N, E> addnode( @NonNull final N... p_nodes )
    {
        return this.addnode( Arrays.stream( p_nodes ) );
    }

    /**
     * static class to create spanning tree
     *
     * @tparam X node type
     * @tparam Y edge type
     */
    private static final class CSpanningTree<X extends INode, Y extends IEdge> extends IBaseGraph<X, Y>
    {

        /**
         * ctor
         *
         * @param p_id identifier / name of the graph
         * @param p_graph graph instance
         */
        CSpanningTree( @NonNull final String p_id, @NonNull final Graph<X, Y> p_graph )
        {
            super( p_id, p_graph );

            m_graph.getEdges().forEach( i -> m_edges.put( i.id(), i ) );
            m_graph.getVertices().forEach( i -> m_nodes.put( i.id(), i ) );
        }
    }
}
