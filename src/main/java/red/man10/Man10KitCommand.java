package red.man10;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.*;
import java.util.UUID;

/**
 * Created by takatronix on 2017/03/06.
 */
public class Man10KitCommand implements CommandExecutor {
    private Man10Kit plugin;

    public Man10KitCommand(Man10Kit plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            showHelp(sender);
            return false;
        }

        ////////////////////////////////////
        //          jail
        ////////////////////////////////////
        if (args[0].equalsIgnoreCase("jail")) {

            if (args.length != 2) {
                sender.sendMessage("/mkit jail [username]");
                return false;
            }
            Player t = Bukkit.getPlayer(args[1]);
            Bukkit.getLogger().info(args[1]);

            if(t.isOnline() == false){
                Bukkit.getLogger().info("player is not online");
                sender.sendMessage(t.getName() +"はオンラインではありません");
                return false;
            }
            //      ユーザーコマンド
            if (sender instanceof Player){
                Player p = (Player) sender;
                plugin.push(p);
                Bukkit.getLogger().info("_jail");
                plugin.load(p,"_jail");
                return true;
            }

        }
        ////////////////////////////////////
        //          push
        ////////////////////////////////////
        if (args[0].equalsIgnoreCase("push")) {


            //    引数がある場合
            if (args.length == 2) {
                String name = args[1];
                Player p = Bukkit.getPlayer(name);
                if(p == null){
                    sender.sendMessage(name+"はオフラインです");
                    return false;
                }
                plugin.push(p);
                sender.sendMessage(name+"のユーザーデータをバックアップしました");
                return true;
            }

            //      ユーザーコマンド
            if (sender instanceof Player){
                Player p = (Player) sender;
                plugin.push(p);
                return true;
            }
            return true;
        }
        ////////////////////////////////////
        //          pop or unjail
        ////////////////////////////////////
        if ((args[0].equalsIgnoreCase("pop")) || (args[0].equalsIgnoreCase("unjail"))) {
            //    引数がある場合

            if (args.length == 2) {
                String name = args[1];
                Player p = Bukkit.getPlayer(name);
                p.sendMessage(args[0]);
                if(p == null){
                    sender.sendMessage(name+"はオフラインです");
                    return false;
                }
                if(plugin.pop(p)){
                    sender.sendMessage(name+"のユーザーデータを復元しました");
                }else{
                    sender.sendMessage(name+"のユーザーデータは存在しない");
                }
                return true;
            }

            //      ユーザーコマンド
            if (sender instanceof Player){
                Player p = (Player) sender;
                plugin.pop(p);
                return true;
            }

            return true;
        }
        ////////////////////////////////////
        //          list
        ////////////////////////////////////
        if (args[0].equalsIgnoreCase("list")) {

            Bukkit.getLogger().info("list");
            if (args.length != 1) {
                sender.sendMessage("/mkit list");
                return false;
            }

            Player p = (Player) sender;
            plugin.list(p);
            return true;
        }
        ////////////////////////////////////
        //          save
        ////////////////////////////////////
        if (args[0].equalsIgnoreCase("save")) {

            Bukkit.getLogger().info("save");
            if (args.length != 2) {
                sender.sendMessage("/mkit save [KitName]");
                return false;
            }
            Player p = (Player) sender;
            plugin.save(p,args[1]);
            return true;
        }
        ////////////////////////////////////
        //          load
        ////////////////////////////////////
        if (args[0].equalsIgnoreCase("load")) {

            Bukkit.getLogger().info("load");
            if (args.length != 2) {
                sender.sendMessage("/mkit load [KitName]");
                return false;
            }
            Player p = (Player) sender;

            plugin.load(p,args[1]);
            return true;
        }
        ////////////////////////////////////
        //          set
        ////////////////////////////////////
        if (args[0].equalsIgnoreCase("set")) {

            Bukkit.getLogger().info("set");
            if (args.length != 3) {
                sender.sendMessage("/mkit set [username] [KitName]");
                return false;
            }

            Player t = Bukkit.getPlayer(args[1]);
            if(t.isOnline() == false){
                Bukkit.getLogger().info("player is not online");
                sender.sendMessage(t.getName() +"はオンラインではありません");
                return false;
            }


            plugin.load(t,args[2]);
            return true;
        }
        ////////////////////////////////////
        //          delete
        ////////////////////////////////////
        if (args[0].equalsIgnoreCase("delete")) {

            Bukkit.getLogger().info("delete");
            if (args.length != 2) {
                sender.sendMessage("/mkit set [KitName]");
                return false;
            }

            Player t = (Player) sender;
            plugin.delete(t,args[1]);
            return true;
        }
        Player t = Bukkit.getPlayer(args[1]);
        showHelp(t);
        return true;
    }

    void showHelp(CommandSender p){
        p.sendMessage("§e==============§d●§f●§a●§e Man10 KitPlugin §d●§f●§a●§e===============");
        p.sendMessage("" +
                "http://man10.red by takatronix\n" +
                "/mkit list - List all kits\n" +
                "/mkit load - Load a kit\n" +
                "/mkit save - Save your inventory to kit\n" +
                "/mkit delete - Delete a saved kit\n" +
                "/mkit push - Push user's inventory\n" +
                "/mkit pop - Pop user's inventory\n"+
                "/mkit jail - Apply Jail Kit\n" +
                "/mkit unuail - Revert user's inventory\n"
        );
    }

}



