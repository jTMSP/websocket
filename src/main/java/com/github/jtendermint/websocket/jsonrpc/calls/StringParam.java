/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2017 - 2018
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.jtendermint.websocket.jsonrpc.calls;

import org.bouncycastle.util.encoders.Base64;

import com.github.jtendermint.crypto.ByteUtil;
import com.github.jtendermint.websocket.jsonrpc.JSONRPC;
import com.github.jtendermint.websocket.jsonrpc.Method;

public class StringParam extends JSONRPC {

    public final String[] params;

    /***
     * Creates a JSON-RPC with String parameters
     * 
     * @param method
     * @param tx00Formatted
     *            the given String must be a 00-formatted ByteString (see
     *            {@link ByteUtil#toString00(byte[])})
     */
    public StringParam(Method method, String tx00Formatted) {
        this(method, tx00Formatted, true);
    }

    /**
     * Creates a JSON-RPC with String parameters
     * 
     * @param m
     * @param string
     *            the given String can be a 00-formatted ByteString (see
     *            {@link ByteUtil#toString00(byte[])})
     * @param isStringTX00formatted
     *            <code>true</code> if String is 00-formated ByteString,
     *            <code>false</code> it will be converted
     */
    public StringParam(Method m, String string, boolean isStringTX00formatted) {
        super(m);
        params = new String[] { isStringTX00formatted ? string : Base64.toBase64String(string.getBytes()) };
    }

    public StringParam(Method m, byte[] bytes) {
        super(m);
        params = new String[] { Base64.toBase64String(bytes) };
    }

    public StringParam(Method m, byte[][] bytes) {
        super(m);

        params = new String[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            params[i] = Base64.toBase64String(bytes[i]);
        }
    }

    public StringParam(Method m, String[] tx00Formatted) {
        super(m);
        params = tx00Formatted;
    }
}
