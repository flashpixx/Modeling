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

package de.tu_clausthal.in.mec.modeling.model.petrinet;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tu_clausthal.in.mec.modeling.model.IModel;
import de.tu_clausthal.in.mec.modeling.model.graph.IGraph;
import de.tu_clausthal.in.mec.modeling.model.graph.INode;
import de.tu_clausthal.in.mec.modeling.model.graph.jung.CDirectedGraph;
import edu.umd.cs.findbugs.annotations.NonNull;

import javax.annotation.Nonnegative;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * petrinet model
 *
 * @todo call and terminate not implemented yet
 */
public final class CPetrinet implements IPetrinet
{
    /**
     * graph structure
     */
    private final IGraph<IPetrinetNode, IPetrinetEdge> m_network;

    /**
     * ctor
     *
     * @param p_name name
     */
    public CPetrinet( @NonNull final String p_name )
    {
        m_network = new CDirectedGraph<>( p_name );
    }

    @Override
    public String id()
    {
        return m_network.id();
    }

    @Override
    public boolean terminated()
    {
        return true;
    }

    @NonNull
    @Override
    @SuppressWarnings( "unchecked" )
    public <N extends IModel<?>> N raw()
    {
        return (N) this;
    }

    @NonNull
    @Override
    public Object serialize()
    {
        return new CSerializer( m_network );
    }

    @Override
    public IPetrinet call() throws Exception
    {
        return this;
    }



    @Override
    public int hashCode()
    {
        return m_network.hashCode();
    }

    @Override
    public boolean equals( final Object p_object )
    {
        return p_object instanceof IModel<?> && p_object.hashCode() == this.hashCode();
    }

    @Override
    public IPetrinet addTransition( @NonNull final String p_id )
    {
        m_network.addnode( new CTransition( p_id ) );
        return this;
    }

    @Override
    public IPetrinet addPlace( @NonNull final String p_id, @NonNull @Nonnegative final Number p_capacity )
    {
        m_network.addnode( new CPlace( p_id, p_capacity ) );
        return this;
    }

    @Override
    public IPetrinet connect( @NonNull final String  p_id, @NonNull final String p_source, @NonNull final String p_target, @NonNull @Nonnegative final Number p_capacity )
    {
        final IPetrinetNode l_source = m_network.node( p_source );
        final IPetrinetNode l_target = m_network.node( p_target );

        if ( !( l_source instanceof IPlace && l_target instanceof ITransition || l_source instanceof ITransition && l_target instanceof IPlace ) )
            throw new RuntimeException(
                MessageFormat.format( "source [{0}] and target [{1}] must be a place and a transition or a transition and a place", p_source, p_target )
            );

        m_network.addedge( l_source, l_target, new CPetrinetEdge( p_id, p_capacity ) );
        return this;
    }

    /**
     * serializing class
     */
    private static final class CSerializer
    {
        /**
         * connections
         */
        @JsonProperty( "edges" )
        private final Set<Map<String, Object>> m_connection;
        /**
         * nodes
         */
        @JsonProperty( "places" )
        private final Map<String, IPlace> m_places;
        /**
         * transitions
         */
        @JsonProperty( "transitions" )
        private final Map<String, ITransition> m_transition;


        /**
         * ctor
         *
         * @param p_graph graph
         */
        CSerializer( @NonNull final IGraph<IPetrinetNode, IPetrinetEdge> p_graph )
        {
            m_connection = p_graph.edges()
                                  .map( i ->
                                  {
                                      final Map.Entry<IPetrinetNode, IPetrinetNode> l_endpoints = p_graph.endpoints( i );

                                      final Map<String, Object> l_map = new HashMap<>();
                                      l_map.put( "from", l_endpoints.getKey().id() );
                                      l_map.put( "to", l_endpoints.getValue().id() );
                                      l_map.put( "capacity", i.get() );

                                      return l_map;
                                  } )
                                  .collect( Collectors.toSet() );

            m_places = p_graph.nodes()
                              .parallel()
                              .filter( i -> i instanceof IPlace )
                              .map( INode::<IPlace>raw )
                              .collect( Collectors.toMap( INode::id, i -> i ) );

            m_transition = p_graph.nodes()
                                  .parallel()
                                  .filter( i -> i instanceof ITransition )
                                  .map( INode::<ITransition>raw )
                                  .collect( Collectors.toMap( INode::id, i -> i ) );
        }
    }
}
