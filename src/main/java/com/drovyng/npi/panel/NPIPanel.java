package com.drovyng.npi.panel;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NPIPanel {
    public static int Updates = 0;
    public String name;
    public Map<Integer, NPIButton> buttons = new HashMap<>(5);
    public NPIPanel(String name){
        this.name = name;
    }
    public void Update(InventoryView inv){
        for(var btn : buttons.entrySet()){
            var button = btn.getValue();
            var item = new ItemStack(button.item.get(Updates % button.item.size()));
            var meta = item.getItemMeta();
            meta.displayName(Component.text(button.name, Style.style(TextDecoration.ITALIC.withState(false),TextColor.color(0xFFFFFF))));
            List<Component> lore = new ArrayList<>();
            for (var line : button.lore.replace("\\n", "\n").split("\n")){
                lore.add(Component.text(line, Style.style(TextDecoration.ITALIC.withState(false), TextColor.fromHexString("#D8D8D8"))));
            }
            meta.lore(lore);
            if (button.customModelData > 0) meta.setCustomModelData(button.customModelData);
            item.setItemMeta(meta);
            inv.setItem(btn.getKey(), item);
        }
    }
}
