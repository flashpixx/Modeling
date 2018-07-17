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

package de.tu_clausthal.in.mec.modeling.model.graph;

import edu.umd.cs.findbugs.annotations.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.stream.Stream;


/**
 * graph interface
 * @param <N>
 * @param <E>
 */
public interface IGraph<N extends INode, E extends IEdge>
{
    /**
     * identifier of the graph
     *
     * @return id
     */
    @NonNull
    String id();

    /**
     * checks if a node exists
     *
     * @param p_id id of the node
     * @return existing flag
     */
    boolean containsnode( @NonNull final String p_id );

    /**
     * check if an edge exists
     *
     * @param p_id edge id
     * @return existing flag
     */
    boolean containsedge( @NonNull final String p_id );

    /**
     * returns an edge by name
     *
     * @param p_id edge id
     * @return edge or null
     */
    @Nullable
    E edge( @NonNull final String p_id );

    /**
     * returns an node by name
     *
     * @param p_id node id
     * @return node or null
     */
    @Nullable
    N node( @NonNull final String p_id );

    /**
     * stream over all edges
     *
     * @return edge stream
     */
    @NonNull
    Stream<E> edges();

    /**
     * stream over all nodes
     *
     * @return node stream
     */
    @NonNull
    Stream<N> nodes();

    /**
     * calculates the shortest path between two nodes
     *
     * @param p_start start node
     * @param p_end end node
     * @return edge stream
     */
    Stream<E> shortestpath( @Nonnull final String p_start, @Nonnull final String p_end );

    /**
     * returns the spanning tree of the graph
     *
     * @param p_id id of the graph
     * @return new graph
     */
    IGraph<N, E> spanningtree( @Nonnull final String p_id );

    /**
     * returns a stream of all in-edges of a node
     *
     * @param p_nodes node sretam
     * @return edge stream
     */
    Stream<E> inedges( @NonNull final Stream<N> p_nodes );

    /**
     * returns a stream of all out-edge of a node
     *
     * @param p_nodes node stream
     * @return edge stream
     */
    Stream<E> outedges( @NonNull final Stream<N> p_nodes );

    /**
     * get stream of node neighbours
     *
     * @param p_nodes node stream
     * @return node stream
     */
    Stream<N> neighbours( @NonNull final Stream<N> p_nodes );

    /**
     * returns a stream of all in-edges of a node
     *
     * @param p_nodes nodes
     * @return edge stream
     */
    @SuppressWarnings( "unchecked" )
    Stream<E> inedges( @NonNull final N... p_nodes );

    /**
     * returns a stream of all out-edge of a node
     *
     * @param p_nodes nodes
     * @return edge stream
     */
    @SuppressWarnings( "unchecked" )
    Stream<E> outedges( @NonNull final N... p_nodes );

    /**
     * get stream of node neighbours
     *
     * @param p_nodes nodes
     * @return node stream
     */
    @SuppressWarnings( "unchecked" )
    Stream<N> neighbours( @NonNull final N... p_nodes );

    /**
     * returns a stream of all in-edges of a node
     *
     * @param p_nodes node ids
     * @return edge stream
     */
    Stream<E> inedges( @NonNull final String... p_nodes );

    /**
     * returns a stream of all out-edge of a node
     *
     * @param p_nodes node ids
     * @return edge stream
     */
    Stream<E> outedges( @NonNull final String... p_nodes );

    /**
     * get stream of node neighbours
     *
     * @param p_nodes node ids
     * @return node stream
     */
    Stream<N> neighbours( @NonNull final String... p_nodes );

    /**
     * removes an edge stream from graph
     *
     * @param p_edges edge stream
     * @return self-reference
     */
    IGraph<N, E> removeedge( @NonNull final Stream<E> p_edges );

    /**
     * removes edges from the graph
     *
     * @param p_edges edges
     * @return self-reference
     */
    @SuppressWarnings( "unchecked" )
    IGraph<N, E> removeedge( @NonNull final E... p_edges );

    /**
     * removes edge by name from the graph
     *
     * @param p_edges  edges
     * @return self-references
     */
    @SuppressWarnings( "unchecked" )
    IGraph<N, E> removeedge( @NonNull final String... p_edges );

    /**
     * removes an node stream from graph
     *
     * @param p_nodes node stream
     * @return self-reference
     */
    IGraph<N, E> removenode( @NonNull final Stream<N> p_nodes );

    /**
     * removes node from the graph
     *
     * @param p_nodes nodes
     * @return self-reference
     */
    @SuppressWarnings( "unchecked" )
    IGraph<N, E> removenode( @NonNull final N... p_nodes );

    /**
     * removes node by name from the graph
     *
     * @param p_nodes nodes
     * @return self-references
     */
    @SuppressWarnings( "unchecked" )
    IGraph<N, E> removenode( @NonNull final String... p_nodes );

    /**
     * adds an edge with nodes to the graph
     *
     * @param p_start start node
     * @param p_end end node
     * @param p_edge edge
     * @return self-reference
     */
    IGraph<N, E> addedge( @NonNull final N p_start, @NonNull final N p_end, @NonNull final E p_edge );

    /**
     * adds a strem of nodes
     *
     * @param p_nodes node stream
     * @return self-reference
     */
    IGraph<N, E> addnode( @NonNull final Stream<N> p_nodes );

    /**
     * adds nodes
     *
     * @param p_nodes nodes
     * @return self-reference
     */
    @SuppressWarnings( "unchecked" )
    IGraph<N, E> addnode( @NonNull final N... p_nodes );

}
