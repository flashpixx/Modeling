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

package de.tu_clausthal.in.mec.modeling.model;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;


/**
 * model storage
 */
public enum EModelStorage implements Function<String, IModel<?>>, Consumer<IModel<?>>
{
    INSTANCE;

    /**
     * model map
     */
    private final Map<String, IModel<?>> m_model = new ConcurrentHashMap<>();

    @Override
    public void accept( @NonNull final IModel<?> p_model )
    {
        m_model.put( p_model.id(), p_model );
    }

    @NonNull
    @Override
    public IModel<?> apply( @NonNull final String p_id )
    {
        final IModel<?> l_model = m_model.get( p_id );
        if ( Objects.isNull( l_model ) )
            throw new RuntimeException( MessageFormat.format( "model [{0}] does not exist", p_id ) );

        return l_model;
    }
}
