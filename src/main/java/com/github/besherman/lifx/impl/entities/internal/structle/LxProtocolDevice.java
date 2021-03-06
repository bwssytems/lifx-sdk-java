/*
 * The MIT License
 *
 * Created by Jarrod Boyes on 24/03/14.
 * Copyright (c) 2014 LIFX Labs. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.besherman.lifx.impl.entities.internal.structle;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.Bool8;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.Float32;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.Int16;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.LxProtocolTypeBase;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.UInt16;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.UInt32;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.UInt64;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.UInt8;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

@SuppressWarnings("unused")
public class LxProtocolDevice {

    public enum Service { // Enum Lx::Protocol::Device::Service
        LX_PROTOCOL_DEVICE_SERVICE_UDP, // LX_PROTOCOL_DEVICE_SERVICE_UDP = 1
        LX_PROTOCOL_DEVICE_SERVICE_TCP,                // LX_PROTOCOL_DEVICE_SERVICE_TCP = 2
    }

    ;

    public static final HashMap<Service, Integer> serviceValueMap;
    public static final HashMap<Integer, Service> serviceMap;

    static {
        serviceValueMap = new HashMap<Service, Integer>();
        serviceMap = new HashMap<Integer, Service>();
        serviceValueMap.put(Service.LX_PROTOCOL_DEVICE_SERVICE_UDP, 1);
        serviceMap.put(1, Service.LX_PROTOCOL_DEVICE_SERVICE_UDP);
        serviceValueMap.put(Service.LX_PROTOCOL_DEVICE_SERVICE_TCP, 2);
        serviceMap.put(2, Service.LX_PROTOCOL_DEVICE_SERVICE_TCP);

    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::SetSite 
    ////////////////////////////////////////////////////////////////////////////   
    public static class SetSite extends LxProtocolTypeBase {

        // Fields: site;
        private byte[] site = new byte[6];        // Field: site - Structle::Bytes byte offset: 0

        private static final int PAYLOAD_SIZE = 6;

        public SetSite(byte[] bytes) {
            this(bytes, 0);
        }

        public SetSite(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("Lx::Protocol::Device::SetSite has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[6];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];

            site = member0Data;
        }

        public SetSite(Object padding, byte[] site) {
            this.site = site;
        }

        public byte[] getSite() {
            return site;
        }

        @Override
        public void printMessageData() {
            Logger.getLogger(SetSite.class.getName()).log(Level.FINE, "Byte Array Print not currently supported");
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, byte[] site) {
            byte[] memberData;        // = name.getBytes();

            memberData = site;

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, byte[] site) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, site);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = site;

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }


    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetPanGateway 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetPanGateway extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetPanGateway() {
        }

        public GetPanGateway(byte[] bytes, int initialOffset) {
        }

        @Override
        public void printMessageData() {
        }

        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StatePanGateway 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StatePanGateway extends LxProtocolTypeBase {
        // Fields: service, port;
        private UInt8 service;            // Field: service - Structle::Uint8 byte offset: 0
        private UInt32 port;            // Field: port - Structle::Uint32 byte offset: 1

        private static final int PAYLOAD_SIZE = 5;

        public StatePanGateway(byte[] bytes) {
            this(bytes, 41);
        }

        public StatePanGateway(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("LX_PROTOCOL_DEVICE_STATE_PAN_GATEWAY has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[1];
            member0Data[0] = bytes[initialOffset + 0];

            service = new UInt8(member0Data);

            byte[] member1Data = new byte[4];
            member1Data[0] = bytes[initialOffset + 1];
            member1Data[1] = bytes[initialOffset + 2];
            member1Data[2] = bytes[initialOffset + 3];
            member1Data[3] = bytes[initialOffset + 4];

            port = new UInt32(member1Data);

        }

        public StatePanGateway(Object padding, UInt8 service, UInt32 port) {
            this.service = service;
            this.port = port;
        }

        public UInt8 getService() {
            return service;
        }

        public UInt32 getPort() {
            return port;
        }

        @Override
        public void printMessageData() {
            service.printValue("service");            // Field: service - Structle::Uint8 byte offset: 5
            port.printValue("port");            // Field: port - Structle::Uint32 byte offset: 5
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt8 service, UInt32 port) {
            byte[] memberData;        // = name.getBytes();

            memberData = service.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = port.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt8 service, UInt32 port) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, service, port);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = service.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = port.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }


    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetTime 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetTime extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetTime(byte[] bytes) {
            this(bytes, 0);
        }

        public GetTime(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("Lx::Protocol::Device::GetTime has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }
        }

        public GetTime(Object padding) {
        }

        @Override
        public void printMessageData() {
        }

        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::SetTime 
    ////////////////////////////////////////////////////////////////////////////   
    public static class SetTime extends LxProtocolTypeBase {
        // Fields: time;
        private UInt64 time;            // Field: time - Structle::Uint64 byte offset: 0

        private static final int PAYLOAD_SIZE = 8;

        public SetTime(byte[] bytes) {
            this(bytes, 0);
        }

        public SetTime(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("Lx::Protocol::Device::SetTime has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[8];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];

            time = new UInt64(member0Data);

        }

        public SetTime(Object padding, UInt64 time) {
            this.time = time;
        }

        public UInt64 getTime() {
            return time;
        }

        @Override
        public void printMessageData() {
            time.printValue("time");            // Field: time - Structle::Uint64 byte offset: 8
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt64 time) {
            byte[] memberData;        // = name.getBytes();

            memberData = time.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt64 time) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, time);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = time.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateTime 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateTime extends LxProtocolTypeBase {
        // Fields: time;
        private UInt64 time;            // Field: time - Structle::Uint64 byte offset: 0

        private static final int PAYLOAD_SIZE = 8;

        public StateTime(byte[] bytes) {
            this(bytes, 0);
        }

        public StateTime(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("LX_PROTOCOL_DEVICE_STATE_TIME has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }


            byte[] member0Data = new byte[8];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];

            time = new UInt64(member0Data);

        }

        public StateTime(Object padding, UInt64 time) {
            this.time = time;
        }

        public UInt64 getTime() {
            return time;
        }

        @Override
        public void printMessageData() {
            time.printValue("time");            // Field: time - Structle::Uint64 byte offset: 8
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt64 time) {
            byte[] memberData;        // = name.getBytes();

            memberData = time.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt64 time) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, time);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = time.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetResetSwitch 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetResetSwitch extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetResetSwitch(byte[] bytes) {
            this(bytes, 0);
        }

        public GetResetSwitch(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("Lx::Protocol::Device::GetResetSwitch has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }
        }

        public GetResetSwitch() {
        }

        @Override
        public void printMessageData() {
        }

        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateResetSwitch 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateResetSwitch extends LxProtocolTypeBase {
        // Fields: position;
        private UInt8 position;            // Field: position - Structle::Uint8 byte offset: 0

        private static final int PAYLOAD_SIZE = 1;

        public StateResetSwitch(byte[] bytes) {
            this(bytes, 0);
        }

        public StateResetSwitch(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("Lx::Protocol::Device::StateResetSwitch has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[1];
            member0Data[0] = bytes[initialOffset + 0];

            position = new UInt8(member0Data);

        }

        public StateResetSwitch(Object padding, UInt8 position) {
            this.position = position;
        }

        public UInt8 getPosition() {
            return position;
        }

        @Override
        public void printMessageData() {
            position.printValue("position");            // Field: position - Structle::Uint8 byte offset: 1
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt8 position) {
            byte[] memberData;        // = name.getBytes();

            memberData = position.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt8 position) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, position);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = position.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetDummyLoad 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetDummyLoad extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetDummyLoad(byte[] bytes) {
            this(bytes, 0);
        }

        public GetDummyLoad(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("Lx::Protocol::Device::GetDummyLoad has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }
        }

        public GetDummyLoad() {
        }

        @Override
        public void printMessageData() {
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset) {
            byte[] memberData;        // = name.getBytes();
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;
            byte[] bytes = new byte[getPayloadSize()];
            byte[] memberData;
            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::SetDummyLoad 
    ////////////////////////////////////////////////////////////////////////////   
    public static class SetDummyLoad extends LxProtocolTypeBase {
        // Fields: on;
        private Bool8 on;            // Field: on - Structle::Bool byte offset: 0

        private static final int PAYLOAD_SIZE = 1;

        public SetDummyLoad(byte[] bytes) {
            this(bytes, 0);
        }

        public SetDummyLoad(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("Lx::Protocol::Device::SetDummyLoad has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[1];
            member0Data[0] = bytes[initialOffset + 0];

            on = new Bool8(member0Data);

        }

        public SetDummyLoad(Object padding, Bool8 on) {
            this.on = on;
        }

        public Bool8 getOn() {
            return on;
        }

        @Override
        public void printMessageData() {
            on.printValue("on");            // Field: on - Structle::Bool byte offset: 1
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Bool8 on) {
            byte[] memberData;        // = name.getBytes();

            memberData = on.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Bool8 on) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, on);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = on.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateDummyLoad 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateDummyLoad extends LxProtocolTypeBase {
        // Fields: on;
        private Bool8 on;            // Field: on - Structle::Bool byte offset: 0

        private static final int PAYLOAD_SIZE = 1;

        public StateDummyLoad(byte[] bytes) {
            this(bytes, 0);
        }

        public StateDummyLoad(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }
            byte[] member0Data = new byte[1];
            member0Data[0] = bytes[initialOffset + 0];

            on = new Bool8(member0Data);
        }

        public StateDummyLoad(Object padding, Bool8 on) {
            this.on = on;
        }

        public Bool8 getOn() {
            return on;
        }

        @Override
        public void printMessageData() {
            on.printValue("on");            // Field: on - Structle::Bool byte offset: 1
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Bool8 on) {
            byte[] memberData;        // = name.getBytes();

            memberData = on.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Bool8 on) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, on);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = on.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetMeshInfo 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetMeshInfo extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetMeshInfo(byte[] bytes) {
            this(bytes, 0);
        }

        public GetMeshInfo(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public GetMeshInfo() {
        }

        @Override
        public void printMessageData() {
        }


        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateMeshInfo 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateMeshInfo extends LxProtocolTypeBase {

        // Fields: signal, tx, rx, mcu_temperature;
        private Float32 signal;                // Field: signal - Structle::Float byte offset: 0
        private UInt32 tx;            // Field: tx - Structle::Uint32 byte offset: 4
        private UInt32 rx;            // Field: rx - Structle::Uint32 byte offset: 8
        private Int16 mcu_temperature;                // Field: mcu_temperature - Structle::Int16 byte offset: 12

        private static final int PAYLOAD_SIZE = 14;

        public StateMeshInfo(byte[] bytes) {
            this(bytes, 0);
        }

        public StateMeshInfo(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[4];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];

            signal = new Float32(member0Data);

            byte[] member1Data = new byte[4];
            member1Data[0] = bytes[initialOffset + 4];
            member1Data[1] = bytes[initialOffset + 5];
            member1Data[2] = bytes[initialOffset + 6];
            member1Data[3] = bytes[initialOffset + 7];

            tx = new UInt32(member1Data);

            byte[] member2Data = new byte[4];
            member2Data[0] = bytes[initialOffset + 8];
            member2Data[1] = bytes[initialOffset + 9];
            member2Data[2] = bytes[initialOffset + 10];
            member2Data[3] = bytes[initialOffset + 11];

            rx = new UInt32(member2Data);

            byte[] member3Data = new byte[2];
            member3Data[0] = bytes[initialOffset + 12];
            member3Data[1] = bytes[initialOffset + 13];

            mcu_temperature = new Int16(member3Data);

        }

        public StateMeshInfo(Object padding, Float32 signal, UInt32 tx, UInt32 rx, Int16 mcu_temperature) {
            this.signal = signal;
            this.tx = tx;
            this.rx = rx;
            this.mcu_temperature = mcu_temperature;
        }

        public Float32 getSignal() {
            return signal;
        }

        public UInt32 getTx() {
            return tx;
        }

        public UInt32 getRx() {
            return rx;
        }

        public Int16 getMcu_temperature() {
            return mcu_temperature;
        }

        @Override
        public void printMessageData() {
            signal.printValue("signal");                // Field: signal - Structle::Float byte offset: 14
            tx.printValue("tx");            // Field: tx - Structle::Uint32 byte offset: 14
            rx.printValue("rx");            // Field: rx - Structle::Uint32 byte offset: 14
            mcu_temperature.printValue("mcu_temperature");                // Field: mcu_temperature - Structle::Int16 byte offset: 14
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Float32 signal, UInt32 tx, UInt32 rx, Int16 mcu_temperature) {
            byte[] memberData;        // = name.getBytes();

            memberData = signal.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = tx.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = rx.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = mcu_temperature.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Float32 signal, UInt32 tx, UInt32 rx, Int16 mcu_temperature) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, signal, tx, rx, mcu_temperature);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = signal.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = tx.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = rx.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = mcu_temperature.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetMeshFirmware 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetMeshFirmware extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetMeshFirmware(byte[] bytes) {
            this(bytes, 0);
        }

        public GetMeshFirmware(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public GetMeshFirmware() {
        }

        @Override
        public void printMessageData() {
        }

        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateMeshFirmware 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateMeshFirmware extends LxProtocolTypeBase {
        // Fields: build, install, version;
        private UInt64 build;            // Field: build - Structle::Uint64 byte offset: 0
        private UInt64 install;            // Field: install - Structle::Uint64 byte offset: 8
        private UInt32 version;            // Field: version - Structle::Uint32 byte offset: 16

        private static final int PAYLOAD_SIZE = 20;

        public StateMeshFirmware(byte[] bytes) {
            this(bytes, 0);
        }

        public StateMeshFirmware(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[8];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];

            build = new UInt64(member0Data);

            byte[] member1Data = new byte[8];
            member1Data[0] = bytes[initialOffset + 8];
            member1Data[1] = bytes[initialOffset + 9];
            member1Data[2] = bytes[initialOffset + 10];
            member1Data[3] = bytes[initialOffset + 11];
            member1Data[4] = bytes[initialOffset + 12];
            member1Data[5] = bytes[initialOffset + 13];
            member1Data[6] = bytes[initialOffset + 14];
            member1Data[7] = bytes[initialOffset + 15];

            install = new UInt64(member1Data);

            byte[] member2Data = new byte[4];
            member2Data[0] = bytes[initialOffset + 16];
            member2Data[1] = bytes[initialOffset + 17];
            member2Data[2] = bytes[initialOffset + 18];
            member2Data[3] = bytes[initialOffset + 19];

            version = new UInt32(member2Data);

        }

        public StateMeshFirmware(Object padding, UInt64 build, UInt64 install, UInt32 version) {
            this.build = build;
            this.install = install;
            this.version = version;
        }

        public UInt64 getBuild() {
            return build;
        }

        public UInt64 getInstall() {
            return install;
        }

        public UInt32 getVersion() {
            return version;
        }

        @Override
        public void printMessageData() {
            build.printValue("build");            // Field: build - Structle::Uint64 byte offset: 20
            install.printValue("install");            // Field: install - Structle::Uint64 byte offset: 20
            version.printValue("version");            // Field: version - Structle::Uint32 byte offset: 20
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt64 build, UInt64 install, UInt32 version) {
            byte[] memberData;        // = name.getBytes();

            memberData = build.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = install.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = version.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt64 build, UInt64 install, UInt32 version) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, build, install, version);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = build.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = install.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = version.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetWifiInfo 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetWifiInfo extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetWifiInfo(byte[] bytes) {
            this(bytes, 0);
        }

        public GetWifiInfo(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public GetWifiInfo() {
        }

        @Override
        public void printMessageData() {
        }


        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateWifiInfo 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateWifiInfo extends LxProtocolTypeBase {
        // Fields: signal, tx, rx, mcu_temperature;
        private Float32 signal;                // Field: signal - Structle::Float byte offset: 0
        private UInt32 tx;            // Field: tx - Structle::Uint32 byte offset: 4
        private UInt32 rx;            // Field: rx - Structle::Uint32 byte offset: 8
        private Int16 mcu_temperature;                // Field: mcu_temperature - Structle::Int16 byte offset: 12

        private static final int PAYLOAD_SIZE = 14;

        public StateWifiInfo(byte[] bytes) {
            this(bytes, 0);
        }

        public StateWifiInfo(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[4];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];

            signal = new Float32(member0Data);

            byte[] member1Data = new byte[4];
            member1Data[0] = bytes[initialOffset + 4];
            member1Data[1] = bytes[initialOffset + 5];
            member1Data[2] = bytes[initialOffset + 6];
            member1Data[3] = bytes[initialOffset + 7];

            tx = new UInt32(member1Data);

            byte[] member2Data = new byte[4];
            member2Data[0] = bytes[initialOffset + 8];
            member2Data[1] = bytes[initialOffset + 9];
            member2Data[2] = bytes[initialOffset + 10];
            member2Data[3] = bytes[initialOffset + 11];

            rx = new UInt32(member2Data);

            byte[] member3Data = new byte[2];
            member3Data[0] = bytes[initialOffset + 12];
            member3Data[1] = bytes[initialOffset + 13];

            mcu_temperature = new Int16(member3Data);

        }

        public StateWifiInfo(Object padding, Float32 signal, UInt32 tx, UInt32 rx, Int16 mcu_temperature) {
            this.signal = signal;
            this.tx = tx;
            this.rx = rx;
            this.mcu_temperature = mcu_temperature;
        }

        public Float32 getSignal() {
            return signal;
        }

        public UInt32 getTx() {
            return tx;
        }

        public UInt32 getRx() {
            return rx;
        }

        public Int16 getMcu_temperature() {
            return mcu_temperature;
        }

        @Override
        public void printMessageData() {
            signal.printValue("signal");                // Field: signal - Structle::Float byte offset: 14
            tx.printValue("tx");            // Field: tx - Structle::Uint32 byte offset: 14
            rx.printValue("rx");            // Field: rx - Structle::Uint32 byte offset: 14
            mcu_temperature.printValue("mcu_temperature");                // Field: mcu_temperature - Structle::Int16 byte offset: 14
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Float32 signal, UInt32 tx, UInt32 rx, Int16 mcu_temperature) {
            byte[] memberData;        // = name.getBytes();

            memberData = signal.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = tx.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = rx.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = mcu_temperature.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Float32 signal, UInt32 tx, UInt32 rx, Int16 mcu_temperature) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, signal, tx, rx, mcu_temperature);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = signal.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = tx.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = rx.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = mcu_temperature.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetWifiFirmware 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetWifiFirmware extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetWifiFirmware(byte[] bytes) {
            this(bytes, 0);
        }

        public GetWifiFirmware(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public GetWifiFirmware() {
        }

        @Override
        public void printMessageData() {
        }

        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateWifiFirmware 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateWifiFirmware extends LxProtocolTypeBase {
        // Fields: build, install, version;
        private UInt64 build;            // Field: build - Structle::Uint64 byte offset: 0
        private UInt64 install;            // Field: install - Structle::Uint64 byte offset: 8
        private UInt32 version;            // Field: version - Structle::Uint32 byte offset: 16

        private static final int PAYLOAD_SIZE = 20;

        public StateWifiFirmware(byte[] bytes) {
            this(bytes, 0);
        }

        public StateWifiFirmware(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[8];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];

            build = new UInt64(member0Data);

            byte[] member1Data = new byte[8];
            member1Data[0] = bytes[initialOffset + 8];
            member1Data[1] = bytes[initialOffset + 9];
            member1Data[2] = bytes[initialOffset + 10];
            member1Data[3] = bytes[initialOffset + 11];
            member1Data[4] = bytes[initialOffset + 12];
            member1Data[5] = bytes[initialOffset + 13];
            member1Data[6] = bytes[initialOffset + 14];
            member1Data[7] = bytes[initialOffset + 15];

            install = new UInt64(member1Data);

            byte[] member2Data = new byte[4];
            member2Data[0] = bytes[initialOffset + 16];
            member2Data[1] = bytes[initialOffset + 17];
            member2Data[2] = bytes[initialOffset + 18];
            member2Data[3] = bytes[initialOffset + 19];

            version = new UInt32(member2Data);
        }

        public StateWifiFirmware(Object padding, UInt64 build, UInt64 install, UInt32 version) {
            this.build = build;
            this.install = install;
            this.version = version;
        }

        public UInt64 getBuild() {
            return build;
        }

        public UInt64 getInstall() {
            return install;
        }

        public UInt32 getVersion() {
            return version;
        }

        @Override
        public void printMessageData() {
            build.printValue("build");            // Field: build - Structle::Uint64 byte offset: 20
            install.printValue("install");            // Field: install - Structle::Uint64 byte offset: 20
            version.printValue("version");            // Field: version - Structle::Uint32 byte offset: 20
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt64 build, UInt64 install, UInt32 version) {
            byte[] memberData;        // = name.getBytes();

            memberData = build.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = install.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = version.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt64 build, UInt64 install, UInt32 version) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, build, install, version);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = build.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = install.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = version.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetPower 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetPower extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetPower(byte[] bytes) {
            this(bytes, 0);
        }

        public GetPower(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public GetPower() {
        }

        @Override
        public void printMessageData() {
        }


        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::SetPower 
    ////////////////////////////////////////////////////////////////////////////   
    public static class SetPower extends LxProtocolTypeBase {
        // Fields: level;
        private UInt16 level;            // Field: level - Structle::Uint16 byte offset: 0

        private static final int PAYLOAD_SIZE = 2;

        public SetPower(byte[] bytes) {
            this(bytes, 0);
        }

        public SetPower(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[2];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];

            level = new UInt16(member0Data);
        }

        public SetPower(UInt16 level) {
            this.level = level;
        }

        public UInt16 getLevel() {
            return level;
        }

        @Override
        public void printMessageData() {
            level.printValue("level");            // Field: level - Structle::Uint16 byte offset: 2
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt16 level) {
            byte[] memberData;        // = name.getBytes();

            memberData = level.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt16 level) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, level);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = level.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StatePower 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StatePower extends LxProtocolTypeBase {
        // Fields: level;
        private UInt16 level;            // Field: level - Structle::Uint16 byte offset: 0

        private static final int PAYLOAD_SIZE = 2;

        public StatePower(byte[] bytes) {
            this(bytes, 0);
        }

        public StatePower(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[2];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];

            level = new UInt16(member0Data);
        }

        public StatePower(UInt16 level) {
            this.level = level;
        }

        public UInt16 getLevel() {
            return level;
        }

        @Override
        public void printMessageData() {
            level.printValue("level");            // Field: level - Structle::Uint16 byte offset: 2
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt16 level) {
            byte[] memberData;        // = name.getBytes();

            memberData = level.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt16 level) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, level);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = level.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetLabel 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetLabel extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetLabel(byte[] bytes) {
            this(bytes, 0);
        }

        public GetLabel(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public GetLabel(Object padding) {
        }

        @Override
        public void printMessageData() {
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset) {
            byte[] memberData;        // = name.getBytes();
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::SetLabel 
    ////////////////////////////////////////////////////////////////////////////   
    public static class SetLabel extends LxProtocolTypeBase {
        // Fields: label;
        private String label;            // Field: label - Structle::String byte offset: 0

        private static final int PAYLOAD_SIZE = 32;

        public SetLabel(byte[] bytes) {
            this(bytes, 0);
        }

        public SetLabel(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[32];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];
            member0Data[8] = bytes[initialOffset + 8];
            member0Data[9] = bytes[initialOffset + 9];
            member0Data[10] = bytes[initialOffset + 10];
            member0Data[11] = bytes[initialOffset + 11];
            member0Data[12] = bytes[initialOffset + 12];
            member0Data[13] = bytes[initialOffset + 13];
            member0Data[14] = bytes[initialOffset + 14];
            member0Data[15] = bytes[initialOffset + 15];
            member0Data[16] = bytes[initialOffset + 16];
            member0Data[17] = bytes[initialOffset + 17];
            member0Data[18] = bytes[initialOffset + 18];
            member0Data[19] = bytes[initialOffset + 19];
            member0Data[20] = bytes[initialOffset + 20];
            member0Data[21] = bytes[initialOffset + 21];
            member0Data[22] = bytes[initialOffset + 22];
            member0Data[23] = bytes[initialOffset + 23];
            member0Data[24] = bytes[initialOffset + 24];
            member0Data[25] = bytes[initialOffset + 25];
            member0Data[26] = bytes[initialOffset + 26];
            member0Data[27] = bytes[initialOffset + 27];
            member0Data[28] = bytes[initialOffset + 28];
            member0Data[29] = bytes[initialOffset + 29];
            member0Data[30] = bytes[initialOffset + 30];
            member0Data[31] = bytes[initialOffset + 31];

            int endOfStringIndex;
            byte[] subString;

            endOfStringIndex = member0Data.length;

            for (int i = 0; i < member0Data.length; i++) {
                if (member0Data[i] == 0x00) {
                    endOfStringIndex = i;
                    break;
                }
            }

            subString = new byte[endOfStringIndex];
            for (int i = 0; i < endOfStringIndex; i++) {
                subString[i] = member0Data[i];
            }

            try {
                label = new String(subString, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new InternalError();
            }

        }

        public SetLabel(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public void printMessageData() {
            //System.out.println( label);			// Field: label - Structle::String byte offset: 32
            Logger.getLogger(SetLabel.class.getName()).log(Level.FINE, label);
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, String label) {
            byte[] memberData;        // = name.getBytes();

            byte[] labelchars;
            try {
                labelchars = label.getBytes("UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new InternalError();
            }

            //byte[] labelBytes = new byte[labelchars.length];
            byte[] labelBytes = new byte[32];

            for (int i = 0; i < 32; i++) {
                labelBytes[i] = 0x00;
            }

            for (int i = 0; i < labelchars.length; i++) {
                labelBytes[i] = labelchars[i];
            }

            memberData = labelBytes;

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, String label) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, label);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            byte[] labelchars;
            try {
                labelchars = label.getBytes("UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new InternalError();
            }
            //byte[] labelBytes = new byte[labelchars.length];
            byte[] labelBytes = new byte[32];

            for (int i = 0; i < 32; i++) {
                labelBytes[i] = 0x00;
            }

            for (int i = 0; i < labelchars.length; i++) {
                labelBytes[i] = labelchars[i];
            }

            memberData = labelBytes;

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateLabel 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateLabel extends LxProtocolTypeBase {
        // Fields: label;
        private String label;            // Field: label - Structle::String byte offset: 0

        private static final int PAYLOAD_SIZE = 32;

        public StateLabel(byte[] bytes) {
            this(bytes, 0);
        }

        public StateLabel(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[32];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];
            member0Data[8] = bytes[initialOffset + 8];
            member0Data[9] = bytes[initialOffset + 9];
            member0Data[10] = bytes[initialOffset + 10];
            member0Data[11] = bytes[initialOffset + 11];
            member0Data[12] = bytes[initialOffset + 12];
            member0Data[13] = bytes[initialOffset + 13];
            member0Data[14] = bytes[initialOffset + 14];
            member0Data[15] = bytes[initialOffset + 15];
            member0Data[16] = bytes[initialOffset + 16];
            member0Data[17] = bytes[initialOffset + 17];
            member0Data[18] = bytes[initialOffset + 18];
            member0Data[19] = bytes[initialOffset + 19];
            member0Data[20] = bytes[initialOffset + 20];
            member0Data[21] = bytes[initialOffset + 21];
            member0Data[22] = bytes[initialOffset + 22];
            member0Data[23] = bytes[initialOffset + 23];
            member0Data[24] = bytes[initialOffset + 24];
            member0Data[25] = bytes[initialOffset + 25];
            member0Data[26] = bytes[initialOffset + 26];
            member0Data[27] = bytes[initialOffset + 27];
            member0Data[28] = bytes[initialOffset + 28];
            member0Data[29] = bytes[initialOffset + 29];
            member0Data[30] = bytes[initialOffset + 30];
            member0Data[31] = bytes[initialOffset + 31];

            int endOfStringIndex = member0Data.length;
            for (int i = 0; i < member0Data.length; i++) {
                if (member0Data[i] == 0x00) {
                    endOfStringIndex = i;
                    break;
                }
            }

            try {
                label = new String(member0Data, 0, endOfStringIndex, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new InternalError("UTF-8 is not avaliable as encoding");
            }
        }

        public StateLabel(Object padding, String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public void printMessageData() {
            //System.out.println( label);			// Field: label - Structle::String byte offset: 32
            Logger.getLogger(StateLabel.class.getName()).log(Level.FINE, label);
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, String label) {
            byte[] labelchars;
            try {
                labelchars = label.getBytes("UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new InternalError();
            }

            byte[] labelBytes = new byte[32];
            for (int i = 0; i < 32; i++) {
                labelBytes[i] = 0x00;
            }

            for (int i = 0; i < labelchars.length; i++) {
                labelBytes[i] = labelchars[i];
            }

            for (int i = 0; i < (labelBytes.length); i++) {
                messageData[(offset + i)] = labelBytes[i];
            }

            offset += labelBytes.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, String label) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, label);
        }

        @Override
        public byte[] getBytes() {
            byte[] bytes = new byte[getPayloadSize()];
            loadMessageDataWithPayloadAtOffset(bytes, 0, label);
            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetTags 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetTags extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetTags(byte[] bytes) {
            this(bytes, 0);
        }

        public GetTags(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public GetTags() {
        }

        @Override
        public void printMessageData() {
        }


        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::SetTags 
    ////////////////////////////////////////////////////////////////////////////   
    public static class SetTags extends LxProtocolTypeBase {
        // Fields: tags;
        private UInt64 tags;            // Field: tags - Structle::Uint64 byte offset: 0

        private static final int PAYLOAD_SIZE = 8;

        public SetTags(byte[] bytes) {
            this(bytes, 0);
        }

        public SetTags(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[8];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];

            tags = new UInt64(member0Data);

        }

        public SetTags(UInt64 tags) {
            this.tags = tags;
        }

        public UInt64 getTags() {
            return tags;
        }

        @Override
        public void printMessageData() {
            tags.printValue("tags");            // Field: tags - Structle::Uint64 byte offset: 8
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt64 tags) {
            byte[] memberData;        // = name.getBytes();

            memberData = tags.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt64 tags) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, tags);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = tags.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateTags 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateTags extends LxProtocolTypeBase {
        // Fields: tags;
        private UInt64 tags;            // Field: tags - Structle::Uint64 byte offset: 0

        private static final int PAYLOAD_SIZE = 8;

        public StateTags(byte[] bytes) {
            this(bytes, 0);
        }

        public StateTags(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[8];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];

            tags = new UInt64(member0Data);

        }

        public StateTags(Object padding, UInt64 tags) {
            this.tags = tags;
        }

        public UInt64 getTags() {
            return tags;
        }

        @Override
        public void printMessageData() {
            tags.printValue("tags");            // Field: tags - Structle::Uint64 byte offset: 8
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt64 tags) {
            byte[] memberData;        // = name.getBytes();

            memberData = tags.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt64 tags) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, tags);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = tags.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetTagLabels 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetTagLabels extends LxProtocolTypeBase {
        // Fields: tags;
        private UInt64 tags;            // Field: tags - Structle::Uint64 byte offset: 0

        private static final int PAYLOAD_SIZE = 8;

        public GetTagLabels(byte[] bytes) {
            this(bytes, 0);
        }

        public GetTagLabels(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[8];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];

            tags = new UInt64(member0Data);

        }

        public GetTagLabels(UInt64 tags) {
            this.tags = tags;
        }

        public UInt64 getTags() {
            return tags;
        }

        @Override
        public void printMessageData() {
            tags.printValue("tags");            // Field: tags - Structle::Uint64 byte offset: 8
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt64 tags) {
            byte[] memberData;        // = name.getBytes();

            memberData = tags.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt64 tags) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, tags);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = tags.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::SetTagLabels 
    ////////////////////////////////////////////////////////////////////////////   
    public static class SetTagLabels extends LxProtocolTypeBase {
        // Fields: tags, label;
        private UInt64 tags;            // Field: tags - Structle::Uint64 byte offset: 0
        private String label;            // Field: label - Structle::String byte offset: 8

        private static final int PAYLOAD_SIZE = 40;

        public SetTagLabels(byte[] bytes) {
            this(bytes, 0);
        }

        public SetTagLabels(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[8];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];

            tags = new UInt64(member0Data);

            byte[] member1Data = new byte[32];
            member1Data[0] = bytes[initialOffset + 8];
            member1Data[1] = bytes[initialOffset + 9];
            member1Data[2] = bytes[initialOffset + 10];
            member1Data[3] = bytes[initialOffset + 11];
            member1Data[4] = bytes[initialOffset + 12];
            member1Data[5] = bytes[initialOffset + 13];
            member1Data[6] = bytes[initialOffset + 14];
            member1Data[7] = bytes[initialOffset + 15];
            member1Data[8] = bytes[initialOffset + 16];
            member1Data[9] = bytes[initialOffset + 17];
            member1Data[10] = bytes[initialOffset + 18];
            member1Data[11] = bytes[initialOffset + 19];
            member1Data[12] = bytes[initialOffset + 20];
            member1Data[13] = bytes[initialOffset + 21];
            member1Data[14] = bytes[initialOffset + 22];
            member1Data[15] = bytes[initialOffset + 23];
            member1Data[16] = bytes[initialOffset + 24];
            member1Data[17] = bytes[initialOffset + 25];
            member1Data[18] = bytes[initialOffset + 26];
            member1Data[19] = bytes[initialOffset + 27];
            member1Data[20] = bytes[initialOffset + 28];
            member1Data[21] = bytes[initialOffset + 29];
            member1Data[22] = bytes[initialOffset + 30];
            member1Data[23] = bytes[initialOffset + 31];
            member1Data[24] = bytes[initialOffset + 32];
            member1Data[25] = bytes[initialOffset + 33];
            member1Data[26] = bytes[initialOffset + 34];
            member1Data[27] = bytes[initialOffset + 35];
            member1Data[28] = bytes[initialOffset + 36];
            member1Data[29] = bytes[initialOffset + 37];
            member1Data[30] = bytes[initialOffset + 38];
            member1Data[31] = bytes[initialOffset + 39];

            int endOfStringIndex;
            byte[] subString;

            endOfStringIndex = member1Data.length;

            for (int i = 0; i < member1Data.length; i++) {
                if (member1Data[i] == 0x00) {
                    endOfStringIndex = i;
                    break;
                }
            }

            subString = new byte[endOfStringIndex];
            for (int i = 0; i < endOfStringIndex; i++) {
                subString[i] = member1Data[i];
            }

            try {
                label = new String(subString, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new InternalError();
            }

        }

        public SetTagLabels(UInt64 tags, String label) {
            this.tags = tags;
            this.label = label;
        }

        public UInt64 getTags() {
            return tags;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public void printMessageData() {
            tags.printValue("tags");            // Field: tags - Structle::Uint64 byte offset: 40
            //System.out.println( label);			// Field: label - Structle::String byte offset: 40
            Logger.getLogger(SetTagLabels.class.getName()).log(Level.FINE, label);
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt64 tags, String label) {
            byte[] memberData;        // = name.getBytes();

            memberData = tags.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            byte[] labelchars;
            try {
                labelchars = label.getBytes("UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new InternalError();
            }

            byte[] labelBytes = new byte[32];

            for (int i = 0; i < 32; i++) {
                labelBytes[i] = 0x00;
            }

            for (int i = 0; i < labelchars.length; i++) {
                labelBytes[i] = labelchars[i];
            }

            memberData = labelBytes;

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt64 tags, String label) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, tags, label);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = tags.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            byte[] labelchars;
            try {
                labelchars = label.getBytes("UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new InternalError();

            }
            //byte[] labelBytes = new byte[labelchars.length];
            byte[] labelBytes = new byte[32];

            for (int i = 0; i < 32; i++) {
                labelBytes[i] = 0x00;
            }

            for (int i = 0; i < labelchars.length; i++) {
                labelBytes[i] = labelchars[i];
            }

            memberData = labelBytes;

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateTagLabels 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateTagLabels extends LxProtocolTypeBase {
        // Fields: tags, label;
        private UInt64 tags;            // Field: tags - Structle::Uint64 byte offset: 0
        private String label;            // Field: label - Structle::String byte offset: 8

        private static final int PAYLOAD_SIZE = 40;

        public StateTagLabels(byte[] bytes) {
            this(bytes, 0);
        }

        public StateTagLabels(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[8];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];

            tags = new UInt64(member0Data);

            byte[] member1Data = new byte[32];
            member1Data[0] = bytes[initialOffset + 8];
            member1Data[1] = bytes[initialOffset + 9];
            member1Data[2] = bytes[initialOffset + 10];
            member1Data[3] = bytes[initialOffset + 11];
            member1Data[4] = bytes[initialOffset + 12];
            member1Data[5] = bytes[initialOffset + 13];
            member1Data[6] = bytes[initialOffset + 14];
            member1Data[7] = bytes[initialOffset + 15];
            member1Data[8] = bytes[initialOffset + 16];
            member1Data[9] = bytes[initialOffset + 17];
            member1Data[10] = bytes[initialOffset + 18];
            member1Data[11] = bytes[initialOffset + 19];
            member1Data[12] = bytes[initialOffset + 20];
            member1Data[13] = bytes[initialOffset + 21];
            member1Data[14] = bytes[initialOffset + 22];
            member1Data[15] = bytes[initialOffset + 23];
            member1Data[16] = bytes[initialOffset + 24];
            member1Data[17] = bytes[initialOffset + 25];
            member1Data[18] = bytes[initialOffset + 26];
            member1Data[19] = bytes[initialOffset + 27];
            member1Data[20] = bytes[initialOffset + 28];
            member1Data[21] = bytes[initialOffset + 29];
            member1Data[22] = bytes[initialOffset + 30];
            member1Data[23] = bytes[initialOffset + 31];
            member1Data[24] = bytes[initialOffset + 32];
            member1Data[25] = bytes[initialOffset + 33];
            member1Data[26] = bytes[initialOffset + 34];
            member1Data[27] = bytes[initialOffset + 35];
            member1Data[28] = bytes[initialOffset + 36];
            member1Data[29] = bytes[initialOffset + 37];
            member1Data[30] = bytes[initialOffset + 38];
            member1Data[31] = bytes[initialOffset + 39];

            int endOfStringIndex;
            byte[] subString;

            endOfStringIndex = member1Data.length;

            for (int i = 0; i < member1Data.length; i++) {
                if (member1Data[i] == 0x00) {
                    endOfStringIndex = i;
                    break;
                }
            }

            subString = new byte[endOfStringIndex];
            for (int i = 0; i < endOfStringIndex; i++) {
                subString[i] = member1Data[i];
            }

            try {
                label = new String(subString, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new InternalError();
            }

        }

        public StateTagLabels(UInt64 tags, String label) {
            this.tags = tags;
            this.label = label;
        }

        public UInt64 getTags() {
            return tags;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public void printMessageData() {
            tags.printValue("tags");            // Field: tags - Structle::Uint64 byte offset: 40
            //System.out.println( label);			// Field: label - Structle::String byte offset: 40
            Logger.getLogger(StateTagLabels.class.getName()).log(Level.FINE, label);
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt64 tags, String label) {
            byte[] memberData;        // = name.getBytes();

            memberData = tags.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            byte[] labelchars;
            try {
                labelchars = label.getBytes("UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new InternalError();
            }

            //byte[] labelBytes = new byte[labelchars.length];
            byte[] labelBytes = new byte[32];

            for (int i = 0; i < 32; i++) {
                labelBytes[i] = 0x00;
            }

            for (int i = 0; i < labelchars.length; i++) {
                labelBytes[i] = labelchars[i];
            }

            memberData = labelBytes;

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt64 tags, String label) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, tags, label);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = tags.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            byte[] labelchars;
            try {
                labelchars = label.getBytes("UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new InternalError();
            }

            //byte[] labelBytes = new byte[labelchars.length];
            byte[] labelBytes = new byte[32];

            for (int i = 0; i < 32; i++) {
                labelBytes[i] = 0x00;
            }

            for (int i = 0; i < labelchars.length; i++) {
                labelBytes[i] = labelchars[i];
            }

            memberData = labelBytes;

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetVersion 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetVersion extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetVersion(byte[] bytes) {
            this(bytes, 0);
        }

        public GetVersion(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public GetVersion() {
        }

        @Override
        public void printMessageData() {
        }


        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateVersion 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateVersion extends LxProtocolTypeBase {
        // Fields: vendor, product, version;
        private UInt32 vendor;            // Field: vendor - Structle::Uint32 byte offset: 0
        private UInt32 product;            // Field: product - Structle::Uint32 byte offset: 4
        private UInt32 version;            // Field: version - Structle::Uint32 byte offset: 8

        private static final int PAYLOAD_SIZE = 12;

        public StateVersion(byte[] bytes) {
            this(bytes, 0);
        }

        public StateVersion(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[4];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];

            vendor = new UInt32(member0Data);

            byte[] member1Data = new byte[4];
            member1Data[0] = bytes[initialOffset + 4];
            member1Data[1] = bytes[initialOffset + 5];
            member1Data[2] = bytes[initialOffset + 6];
            member1Data[3] = bytes[initialOffset + 7];

            product = new UInt32(member1Data);

            byte[] member2Data = new byte[4];
            member2Data[0] = bytes[initialOffset + 8];
            member2Data[1] = bytes[initialOffset + 9];
            member2Data[2] = bytes[initialOffset + 10];
            member2Data[3] = bytes[initialOffset + 11];

            version = new UInt32(member2Data);

        }

        public StateVersion(UInt32 vendor, UInt32 product, UInt32 version) {
            this.vendor = vendor;
            this.product = product;
            this.version = version;
        }

        public UInt32 getVendor() {
            return vendor;
        }

        public UInt32 getProduct() {
            return product;
        }

        public UInt32 getVersion() {
            return version;
        }

        @Override
        public void printMessageData() {
            vendor.printValue("vendor");            // Field: vendor - Structle::Uint32 byte offset: 12
            product.printValue("product");            // Field: product - Structle::Uint32 byte offset: 12
            version.printValue("version");            // Field: version - Structle::Uint32 byte offset: 12
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt32 vendor, UInt32 product, UInt32 version) {
            byte[] memberData;        // = name.getBytes();

            memberData = vendor.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = product.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = version.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt32 vendor, UInt32 product, UInt32 version) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, vendor, product, version);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = vendor.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = product.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = version.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetInfo 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetInfo extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetInfo(byte[] bytes) {
            this(bytes, 0);
        }

        public GetInfo(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public GetInfo() {
        }

        @Override
        public void printMessageData() {
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset) {
            byte[] memberData;        // = name.getBytes();
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateInfo 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateInfo extends LxProtocolTypeBase {
        // Fields: time, uptime, downtime;
        private UInt64 time;            // Field: time - Structle::Uint64 byte offset: 0
        private UInt64 uptime;            // Field: uptime - Structle::Uint64 byte offset: 8
        private UInt64 downtime;            // Field: downtime - Structle::Uint64 byte offset: 16

        private static final int PAYLOAD_SIZE = 24;

        public StateInfo(byte[] bytes) {
            this(bytes, 0);
        }

        public StateInfo(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[8];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];

            time = new UInt64(member0Data);

            byte[] member1Data = new byte[8];
            member1Data[0] = bytes[initialOffset + 8];
            member1Data[1] = bytes[initialOffset + 9];
            member1Data[2] = bytes[initialOffset + 10];
            member1Data[3] = bytes[initialOffset + 11];
            member1Data[4] = bytes[initialOffset + 12];
            member1Data[5] = bytes[initialOffset + 13];
            member1Data[6] = bytes[initialOffset + 14];
            member1Data[7] = bytes[initialOffset + 15];

            uptime = new UInt64(member1Data);

            byte[] member2Data = new byte[8];
            member2Data[0] = bytes[initialOffset + 16];
            member2Data[1] = bytes[initialOffset + 17];
            member2Data[2] = bytes[initialOffset + 18];
            member2Data[3] = bytes[initialOffset + 19];
            member2Data[4] = bytes[initialOffset + 20];
            member2Data[5] = bytes[initialOffset + 21];
            member2Data[6] = bytes[initialOffset + 22];
            member2Data[7] = bytes[initialOffset + 23];

            downtime = new UInt64(member2Data);

        }

        public StateInfo(Object padding, UInt64 time, UInt64 uptime, UInt64 downtime) {
            this.time = time;
            this.uptime = uptime;
            this.downtime = downtime;
        }

        public UInt64 getTime() {
            return time;
        }

        public UInt64 getUptime() {
            return uptime;
        }

        public UInt64 getDowntime() {
            return downtime;
        }

        @Override
        public void printMessageData() {
            time.printValue("time");            // Field: time - Structle::Uint64 byte offset: 24
            uptime.printValue("uptime");            // Field: uptime - Structle::Uint64 byte offset: 24
            downtime.printValue("downtime");            // Field: downtime - Structle::Uint64 byte offset: 24
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt64 time, UInt64 uptime, UInt64 downtime) {
            byte[] memberData;        // = name.getBytes();

            memberData = time.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = uptime.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = downtime.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt64 time, UInt64 uptime, UInt64 downtime) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, time, uptime, downtime);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = time.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = uptime.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = downtime.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetMcuRailVoltage 
    ////////////////////////////////////////////////////////////////////////////   
    public static class GetMcuRailVoltage extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public GetMcuRailVoltage(byte[] bytes) {
            this(bytes, 0);
        }

        public GetMcuRailVoltage(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public GetMcuRailVoltage() {
        }

        @Override
        public void printMessageData() {
        }


        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateMcuRailVoltage 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateMcuRailVoltage extends LxProtocolTypeBase {
        // Fields: voltage;
        private UInt32 voltage;            // Field: voltage - Structle::Uint32 byte offset: 0

        private static final int PAYLOAD_SIZE = 4;

        public StateMcuRailVoltage(byte[] bytes) {
            this(bytes, 0);
        }

        public StateMcuRailVoltage(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[4];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];

            voltage = new UInt32(member0Data);
        }

        public StateMcuRailVoltage(Object padding, UInt32 voltage) {
            this.voltage = voltage;
        }

        public UInt32 getVoltage() {
            return voltage;
        }

        @Override
        public void printMessageData() {
            voltage.printValue("voltage");            // Field: voltage - Structle::Uint32 byte offset: 4
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt32 voltage) {
            byte[] memberData;        // = name.getBytes();

            memberData = voltage.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt32 voltage) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, voltage);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = voltage.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::Reboot 
    ////////////////////////////////////////////////////////////////////////////   
    public static class Reboot extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public Reboot(byte[] bytes) {
            this(bytes, 0);
        }

        public Reboot(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public Reboot(Object padding) {
        }

        @Override
        public void printMessageData() {
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset) {
            byte[] memberData;        // = name.getBytes();
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::SetFactoryTestMode 
    ////////////////////////////////////////////////////////////////////////////   
    public static class SetFactoryTestMode extends LxProtocolTypeBase {
        // Fields: on;
        private Bool8 on;            // Field: on - Structle::Bool byte offset: 0

        private static final int PAYLOAD_SIZE = 1;

        public SetFactoryTestMode(byte[] bytes) {
            this(bytes, 0);
        }

        public SetFactoryTestMode(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[1];
            member0Data[0] = bytes[initialOffset + 0];

            on = new Bool8(member0Data);

        }

        public SetFactoryTestMode(Object padding, Bool8 on) {
            this.on = on;
        }

        public Bool8 getOn() {
            return on;
        }

        @Override
        public void printMessageData() {
            on.printValue("on");            // Field: on - Structle::Bool byte offset: 1
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Bool8 on) {
            byte[] memberData;        // = name.getBytes();

            memberData = on.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Bool8 on) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, on);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = on.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::DisableFactoryTestMode 
    ////////////////////////////////////////////////////////////////////////////   
    public static class DisableFactoryTestMode extends LxProtocolTypeBase {
        private static final int PAYLOAD_SIZE = 0;

        public DisableFactoryTestMode(byte[] bytes) {
            this(bytes, 0);
        }

        public DisableFactoryTestMode(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public DisableFactoryTestMode(Object padding) {
        }

        @Override
        public void printMessageData() {
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset) {
            byte[] memberData;        // = name.getBytes();
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateFactoryTestMode 
    ////////////////////////////////////////////////////////////////////////////   
    public static class StateFactoryTestMode extends LxProtocolTypeBase {
        // Fields: on, disabled;
        private Bool8 on;            // Field: on - Structle::Bool byte offset: 0
        private Bool8 disabled;            // Field: disabled - Structle::Bool byte offset: 1

        private static final int PAYLOAD_SIZE = 2;

        public StateFactoryTestMode(byte[] bytes) {
            this(bytes, 0);
        }

        public StateFactoryTestMode(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member0Data = new byte[1];
            member0Data[0] = bytes[initialOffset + 0];

            on = new Bool8(member0Data);

            byte[] member1Data = new byte[1];
            member1Data[0] = bytes[initialOffset + 1];

            disabled = new Bool8(member1Data);

        }

        public StateFactoryTestMode(Object padding, Bool8 on, Bool8 disabled) {
            this.on = on;
            this.disabled = disabled;
        }

        public Bool8 getOn() {
            return on;
        }

        public Bool8 getDisabled() {
            return disabled;
        }

        @Override
        public void printMessageData() {
            on.printValue("on");            // Field: on - Structle::Bool byte offset: 2
            disabled.printValue("disabled");            // Field: disabled - Structle::Bool byte offset: 2
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Bool8 on, Bool8 disabled) {
            byte[] memberData;        // = name.getBytes();

            memberData = on.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = disabled.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Bool8 on, Bool8 disabled) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, on, disabled);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = on.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = disabled.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetLocation
    ////////////////////////////////////////////////////////////////////////////
    public static class GetLocation extends LxProtocolTypeBase {

        private static final int PAYLOAD_SIZE = 0;

        public GetLocation(byte[] bytes) {
            this(bytes, 0);
        }

        public GetLocation(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public GetLocation() {
        }

        @Override
        public void printMessageData() {
        }


        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateLocation
    ////////////////////////////////////////////////////////////////////////////
    public static class StateLocation extends LxProtocolTypeBase {
        // Fields: location, label, updated_at
        private byte[] location = new byte[16]; // Field: location - byte[16], offset 0
        private String label;                   // Field: label - Structle::String 32 bytes, offset 16
        private UInt64 updated_at;              // Field: updated_at - Structle::Uint64 8 bytes, offset 48

        private static final int PAYLOAD_SIZE = 56;

        public StateLocation(byte[] bytes) {
            this(bytes, 0);
        }

        public StateLocation(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            System.arraycopy(bytes, initialOffset, location, 0, 16);

            int stringLength = 32;
            int offset = initialOffset + 16;
            label = extractStringFromByteArray(bytes, stringLength, offset);

            byte[] updatedAtBytes = new byte[8];
            System.arraycopy(bytes, initialOffset + 48, updatedAtBytes, 0, 8);
            updated_at = new UInt64(updatedAtBytes);
        }

        public StateLocation(byte[] location, String label, UInt64 updated_at) {
            this.location = location;
            this.label = label;
            this.updated_at = updated_at;
        }

        @Override
        public void printMessageData() {
            // location.printValue("location");
            // System.out.println(label);
            updated_at.printValue("updated_at");
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;
            byte[] bytes = new byte[PAYLOAD_SIZE];

            System.arraycopy(location, 0, bytes, offset, 16);

            offset = 16;

            offset = addStringToByteArray(label, bytes, 32, offset);

            addUint64ToByteArray(updated_at, bytes, offset);

            return bytes;
        }

        public String getLabel() {
            return label;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::GetGroup
    ////////////////////////////////////////////////////////////////////////////
    public static class GetGroup extends LxProtocolTypeBase {

        private static final int PAYLOAD_SIZE = 0;

        public GetGroup(byte[] bytes) {
            this(bytes, 0);
        }

        public GetGroup(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

        }

        public GetGroup() {
        }

        @Override
        public void printMessageData() {
        }


        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::StateGroup
    ////////////////////////////////////////////////////////////////////////////
    public static class StateGroup extends LxProtocolTypeBase {
        // Fields: location, label, updated_at
        private byte[] group = new byte[16]; // Field: group - byte[16], offset 0
        private String label;                   // Field: label - Structle::String 32 bytes, offset 16
        private UInt64 updated_at;              // Field: updated_at - Structle::Uint64 8 bytes, offset 48

        private static final int PAYLOAD_SIZE = 56;

        public StateGroup(byte[] bytes) {
            this(bytes, 0);
        }

        public StateGroup(byte[] bytes, int initialOffset) {
            if (bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }

            System.arraycopy(bytes, initialOffset, group, 0, 16);

            int stringLength = 32;
            int offset = initialOffset + 16;
            label = extractStringFromByteArray(bytes, stringLength, offset);

            byte[] updatedAtBytes = new byte[8];
            System.arraycopy(bytes, initialOffset + 48, updatedAtBytes, 0, 8);
            updated_at = new UInt64(updatedAtBytes);
        }

        public StateGroup(byte[] group, String label, UInt64 updated_at) {
            this.group = group;
            this.label = label;
            this.updated_at = updated_at;
        }

        @Override
        public void printMessageData() {
            // group.printValue("group");
            // System.out.println(label);
            updated_at.printValue("updated_at");
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;
            byte[] bytes = new byte[PAYLOAD_SIZE];

            System.arraycopy(group, 0, bytes, offset, 16);

            offset = 16;

            offset = addStringToByteArray(label, bytes, 32, offset);

            addUint64ToByteArray(updated_at, bytes, offset);

            return bytes;
        }

        public String getLabel() {
            return label;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::EchoRequest
    ////////////////////////////////////////////////////////////////////////////
    public static class EchoRequest extends LxProtocolTypeBase {

        private static final int PAYLOAD_SIZE = 64;
        private byte[] thebytes;
        public EchoRequest(byte[] bytes) {
            this(bytes, 0);
        }

        public EchoRequest(byte[] bytes, int initialOffset) {
            thebytes = new byte[bytes.length];
            System.arraycopy(bytes, initialOffset, thebytes, 0, bytes.length);
        }

        public EchoRequest() {
        }

        @Override
        public void printMessageData() {
        }


        @Override
        public byte[] getBytes() {
            return thebytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Device::EchoResponse
    ////////////////////////////////////////////////////////////////////////////
    public static class EchoResponse extends LxProtocolTypeBase {

        private static final int PAYLOAD_SIZE = 64;

        public EchoResponse(byte[] bytes) {
            this(bytes, 0);
        }

        public EchoResponse(byte[] bytes, int initialOffset) {
        }

        public EchoResponse() {
        }

        @Override
        public void printMessageData() {
        }


        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    private static int addStringToByteArray(String s, byte[] arr, int stringLength, int offset) {
        byte[] labelchars;
        try {
            labelchars = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new InternalError();
        }
        byte[] labelBytes = new byte[stringLength];

        for (int i = 0; i < stringLength; i++) {
            labelBytes[i] = 0x00;
        }

        System.arraycopy(labelchars, 0, labelBytes, 0, labelchars.length);

        System.arraycopy(labelBytes, 0, arr, offset, stringLength);
        return offset + labelchars.length;
    }

    private static int addUint64ToByteArray(UInt64 uInt64, byte[] arr, int offset) {
        byte[] uintBytes = uInt64.getBytes();
        System.arraycopy(uintBytes, 0, arr, offset, uintBytes.length);
        return offset + uintBytes.length;
    }

    private static String extractStringFromByteArray(byte[] bytes, int stringLength, int offset) {
        byte[] labelBytes = new byte[stringLength];
        System.arraycopy(bytes, offset, labelBytes, 0, stringLength);

        int endOfStringIndex;
        byte[] subString;

        endOfStringIndex = labelBytes.length;

        for (int i = 0; i < labelBytes.length; i++) {
            if (labelBytes[i] == 0x00) {
                endOfStringIndex = i;
                break;
            }
        }

        subString = new byte[endOfStringIndex];
        System.arraycopy(labelBytes, 0, subString, 0, endOfStringIndex);

        String label = "";
        try {
            label = new String(subString, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new InternalError();
        }
        return label;
    }
}
