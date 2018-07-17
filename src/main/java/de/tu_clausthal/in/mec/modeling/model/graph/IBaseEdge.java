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
import java.util.function.Supplier;


/**
 * abstract class of an edge
 */
public abstract class IBaseEdge implements IEdge
{
    /**
     * id of the edge
     */
    protected final String m_id;
    /**
     * weight function
     */
    private final Supplier<Number> m_weightfunction;

    /**
     * ctor
     *
     * @param p_id edge id
     */
    protected IBaseEdge( @NonNull final String p_id )
    {
        this( p_id, () -> 0 );
    }

    /**
     * ctor
     *
     * @param p_id edge id
     * @param p_weightfunction weight function
     */
    protected IBaseEdge( @NonNull final String p_id, @NonNull final Supplier<Number> p_weightfunction )
    {
        m_id = p_id;
        m_weightfunction = p_weightfunction;
    }

    @Override
    public final int hashCode()
    {
        return m_id.hashCode();
    }

    @Override
    public final boolean equals( final Object p_object )
    {
        return p_object instanceof IEdge && p_object.hashCode() == this.hashCode();
    }

    @NonNull
    @Override
    public final String id()
    {
        return m_id;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public final <T extends IEdge> T raw()
    {
        return (T) this;
    }

    @Nonnull
    @Override
    public final Number get()
    {
        return m_weightfunction.get();
    }
}
