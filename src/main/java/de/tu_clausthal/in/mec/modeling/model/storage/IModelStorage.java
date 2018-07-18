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

package de.tu_clausthal.in.mec.modeling.model.storage;

import de.tu_clausthal.in.mec.modeling.model.IModel;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;


/**
 * interface for model storage
 */
public interface IModelStorage extends Function<String, IModel<?>>, Supplier<Stream<IModel<?>>>
{
    /**
     * adds a new model
     *
     * @param p_model model
     * @return model
     */
    @NonNull
    IModel<?> add( @NonNull final IModel<?> p_model );

    /**
     * removes a model
     *
     * @param p_id id
     * @return removed model
     */
    @NonNull
    IModel<?> remove( @NonNull final String p_id );
}
