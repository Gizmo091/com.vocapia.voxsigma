package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionString;

/**
 * Host option is used to force a specific node to treat the query.
 * [Internal usage only]
 * Node name are not public.
 */
public class Host extends OptionString {

    public Host(String value) {
        super("host", value);
    }
}
