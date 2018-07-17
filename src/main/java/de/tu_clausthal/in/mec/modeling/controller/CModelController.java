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

package de.tu_clausthal.in.mec.modeling.controller;

import de.tu_clausthal.in.mec.modeling.model.storage.EModelStorage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * model rest controller
 */
@RestController
@RequestMapping( "/model" )
public final class CModelController
{

    /**
     * get model
     *
     * @param p_id name
     * @return petrinet structure
     */
    @RequestMapping( value = "/get/{id}" )
    public Object get( @PathVariable( "id" ) final String p_id )
    {
        return EModelStorage.INSTANCE.apply( p_id ).serialize();
    }

    /**
     * remove model
     *
     * @param p_id name
     */
    @RequestMapping( value = "/remove/{id}" )
    public void remove( @PathVariable( "id" ) final String p_id )
    {
        EModelStorage.INSTANCE.remove( p_id );
    }

}
