package com.hzm;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.SerialParameters;
import com.intelligt.modbus.jlibmodbus.serial.SerialPort;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

/**
 *
 * @author Hezeming
 * @version 1.0
 * @date 2021年02月02日
 */
@Slf4j
@Data
public class Test {

    private int price;
    private int age;

    public static void main(String[] args) {
//        System.out.println(Integer.parseInt("0A", 16));


    }

    public static void rtu() {
        try {
            SerialParameters serialParameters = new SerialParameters();
            // 设定MODBUS通讯的串行口
//            serialParameters.setCommPortId("com6");
            // 设定成无奇偶校验
            serialParameters.setParity(SerialPort.Parity.NONE);
            // 设定成数据位是8位
            serialParameters.setDataBits(8);
            // 设定为1个停止位
            serialParameters.setStopBits(1);
            // 设定端口名称
//            serialParameters.setPortOwnerName("Numb nuts");
            // 设定端口波特率
            serialParameters.setBaudRate(SerialPort.BaudRate.BAUD_RATE_9600);

            serialParameters.setDevice("100");

            // 创建一个主机
            ModbusMaster master = ModbusMasterFactory.createModbusMasterRTU(serialParameters);
            Modbus.setAutoIncrementTransactionId(true);

            int slaveId = 1;//从机地址
            int offset = 100;//寄存器读取开始地址
            int quantity = 10;//读取的寄存器数量


            try {
                if (!master.isConnected()) {
                    master.connect();// 开启连接
                }

                // 读取对应从机的数据，readInputRegisters读取的读寄存器，功能码03
                int[] registerValues = master.readHoldingRegisters(slaveId, offset, quantity);

                // 控制台输出
                for (int value : registerValues) {
                    System.out.println("Address: " + offset++ + ", Value: " + value);
                }

            } catch (ModbusProtocolException e) {
                e.printStackTrace();
            } catch (ModbusNumberException e) {
                e.printStackTrace();
            } catch (ModbusIOException e) {
                e.printStackTrace();
            } finally {
                try {
                    master.disconnect();
                } catch (ModbusIOException e) {
                    e.printStackTrace();
                }
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void tcp() {
        try {
            // 设置主机TCP参数
            TcpParameters tcpParameters = new TcpParameters();

            // 设置TCP的ip地址
            InetAddress adress = InetAddress.getByName("127.0.0.1");

            // TCP参数设置ip地址
            // tcpParameters.setHost(InetAddress.getLocalHost());
            tcpParameters.setHost(adress);

            // TCP设置长连接
            tcpParameters.setKeepAlive(true);
            // TCP设置端口，这里设置是默认端口502
            tcpParameters.setPort(Modbus.TCP_PORT);

            // 创建一个主机
            ModbusMaster master = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
            Modbus.setAutoIncrementTransactionId(true);

            int slaveId = 1;//从机地址
            int offset = 100;//寄存器读取开始地址
            int quantity = 10;//读取的寄存器数量


            try {
                if (!master.isConnected()) {
                    master.connect();// 开启连接
                }

                // 读取对应从机的数据，readInputRegisters读取的读寄存器，功能码03
//                int[] registerValues = master.readInputRegisters(slaveId, offset, quantity);
                // 读取对应从机的数据，readInputRegisters读取的写寄存器，功能码04
                int[] registerValues = master.readHoldingRegisters(slaveId, offset, quantity);

                // 控制台输出
                for (int value : registerValues) {
                    System.out.println("Address: " + offset++ + ", Value: " + value);
                }

            } catch (ModbusProtocolException e) {
                e.printStackTrace();
            } catch (ModbusNumberException e) {
                e.printStackTrace();
            } catch (ModbusIOException e) {
                e.printStackTrace();
            } finally {
                try {
                    master.disconnect();
                } catch (ModbusIOException e) {
                    e.printStackTrace();
                }
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
