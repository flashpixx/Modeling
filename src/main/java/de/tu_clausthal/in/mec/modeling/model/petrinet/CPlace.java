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

import de.tu_clausthal.in.mec.modeling.model.graph.IBaseNode;
import edu.umd.cs.findbugs.annotations.NonNull;

import javax.annotation.Nonnegative;
import java.text.MessageFormat;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Stream;


/**
 * petrinet place
 */
public final class CPlace extends IBaseNode implements IPlace
{
    /**
     * set with marks
     */
    private final Set<IMark> m_marks = new CopyOnWriteArraySet<>();
    /**
     * capacity
     */
    private final Number m_capacity;

    /**
     * ctor
     *
     * @param p_id id of the place
     */
    public CPlace( @NonNull final String p_id )
    {
        this( p_id, Integer.MAX_VALUE );
    }

    /**
     * ctor
     *
     * @param p_id id of the place
     * @param p_capacity capacity
     */
    public CPlace( @NonNull final String p_id, @Nonnegative final Number p_capacity )
    {
        super( p_id );
        m_capacity = p_capacity;
    }

    @Override
    public Number get()
    {
        return m_marks.size();
    }

    @Override
    public void accept( @NonNull final Stream<IMark> p_stream )
    {
        if ( p_stream.peek( m_marks::add ).anyMatch( i -> m_marks.size() > m_capacity.longValue() ) )
            throw new RuntimeException( MessageFormat.format( "place [{0}] with capacity [{1}] is full", m_id, m_capacity ) );
    }

    @Override
    public Stream<IMark> apply( @NonNull final Number p_number )
    {
        return m_marks.stream().peek( m_marks::remove ).limit( p_number.longValue() );
    }
}
