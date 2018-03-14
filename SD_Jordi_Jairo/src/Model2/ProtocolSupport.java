package Model;

import Controller.ComUtils;
import java.io.IOException;


public class ProtocolSupport {

    public int receive_UID(ComUtils utils) {
        int intContent = -1;
        String cmd_received = "";
        while (cmd_received != "UID") {
            try {
                cmd_received = utils.read_command3();
                if (cmd_received == "UID") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        while (cmd_received != " ") {
            try {
                cmd_received = utils.read_space();
                if (cmd_received == " ") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (intContent == -1) {
            try {
                intContent = utils.read_int32();
                if (intContent != -1) {
                    return intContent;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return intContent;
    }


    public void send_STK_TURN(ComUtils utils, Game game) {
        try {
            // Sending stack
            utils.write_stack();
            utils.write_space();
            utils.write_int32(game.getStackClient());
            utils.write_space();
            utils.write_int32(game.getStackServer());
            // Sending turn
            utils.write_turn();
            utils.write_space();
            utils.write_char(game.getTurn());
        } catch (Exception e) {
            System.out.println("Unable to write.");
        }
    }

    public int receive_BET(ComUtils utils) {
        String cmd_received = "";
        while (cmd_received != "BET") {
            try {
                cmd_received = utils.read_command3();
                if (cmd_received == "BET") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return 1;
    }

    public void send_CRD(ComUtils utils, Card c) {
        try {
            utils.write_card();
            utils.write_space();
            utils.write_card_code(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String receive_SHW(ComUtils utils) {
        String cmd_received = "";
        while (cmd_received != "SHW") {
            try {
                cmd_received = utils.read_command3();
                if (cmd_received == "SHW") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        while (cmd_received != " ") {
            try {
                cmd_received = utils.read_space();
                if (cmd_received == " ") {
                    continue;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (cmd_received != "J" || cmd_received != "K" || cmd_received != "Q") {
            try {
                cmd_received = utils.read_card();
                if (cmd_received == "J" || cmd_received == "K" || cmd_received == "Q") {
                    return cmd_received;
                } else {
                    utils.write_error();
                    utils.write_space();
                    utils.write_error_num(02);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void send_WIN(ComUtils utils, String winner) {
        try {
            utils.write_win();
            utils.write_space();
            utils.write_char(winner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
