/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.automation.parser.gson.internal;

import java.io.OutputStreamWriter;
import java.util.Set;

import org.eclipse.smarthome.automation.Rule;
import org.eclipse.smarthome.automation.parser.Parser;
import org.eclipse.smarthome.automation.template.RuleTemplate;
import org.eclipse.smarthome.automation.type.CompositeActionType;
import org.eclipse.smarthome.automation.type.CompositeConditionType;
import org.eclipse.smarthome.automation.type.CompositeTriggerType;
import org.eclipse.smarthome.config.core.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Abstract class that can be used by the parsers for the different entity types.
 *
 * @author Kai Kreuzer - Initial contribution
 * @author Ana Dimova - add Instance Creators
 *
 * @param <T> the type of the entities to parse
 */
public abstract class AbstractGSONParser<T> implements Parser<T> {

    // A Gson instance to use by the parsers
    static protected Gson gson = new GsonBuilder() //
            .registerTypeAdapter(Rule.class, new RuleInstanceCreator()) //
            .registerTypeAdapter(RuleTemplate.class, new TemplateInstanceCreator()) //
            .registerTypeAdapter(CompositeActionType.class, new ActionInstanceCreator()) //
            .registerTypeAdapter(CompositeConditionType.class, new ConditionInstanceCreator()) //
            .registerTypeAdapter(CompositeTriggerType.class, new TriggerInstanceCreator()) //
            .registerTypeAdapter(Configuration.class, new ConfigurationDeserializer()) //
            .registerTypeAdapter(Configuration.class, new ConfigurationSerializer()) //
            .create();

    @Override
    public void serialize(Set<T> dataObjects, OutputStreamWriter writer) throws Exception {
        gson.toJson(dataObjects, writer);
    }
}
