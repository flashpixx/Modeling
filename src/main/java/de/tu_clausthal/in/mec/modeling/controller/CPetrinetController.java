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
import de.tu_clausthal.in.mec.modeling.model.petri.CPetrinet;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * petri-net rest controller
 */
@RestController
@RequestMapping( "/petrinet" )
public final class CPetrinetController
{
    /**
     * creates a new petrinet
     *
     * @param p_name name
     */
    @RequestMapping( value = "/create/{name}" )
    public void create( @PathVariable( "name" ) final String p_name )
    {
        EModelStorage.INSTANCE.add( new CPetrinet( p_name ) ).serialize();
    }

    /**
     * remove petrinet
     *
     * @param p_name name
     */
    @RequestMapping( value = "/remove/{name}" )
    public void remove( @PathVariable( "name" ) final String p_name )
    {
        EModelStorage.INSTANCE.add( new CPetrinet( p_name ) );
    }

    /**
     * get petrinet
     *
     * @param p_name name
     * @return petrinet structure
     */
    @RequestMapping( value = "/get/{name}" )
    public Object get( @PathVariable( "name" ) final String p_name )
    {
        return EModelStorage.INSTANCE.apply( p_name ).serialize();
    }

}
