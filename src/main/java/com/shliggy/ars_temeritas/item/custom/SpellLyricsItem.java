package com.shliggy.ars_temeritas.item.custom;

import com.hollingsworth.arsnouveau.api.item.ICasterTool;
import com.hollingsworth.arsnouveau.api.registry.SpellCasterRegistry;
import com.hollingsworth.arsnouveau.api.spell.AbstractCaster;
import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.api.spell.SpellCaster;
import com.hollingsworth.arsnouveau.client.gui.SpellTooltip;
import com.hollingsworth.arsnouveau.common.items.ModItem;
import com.hollingsworth.arsnouveau.common.items.SpellBook;
import com.hollingsworth.arsnouveau.common.items.SpellParchment;
import com.hollingsworth.arsnouveau.common.util.PortUtil;
import com.hollingsworth.arsnouveau.setup.config.Config;
import com.hollingsworth.arsnouveau.setup.registry.DataComponentRegistry;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SpellLyricsItem extends ModItem implements ICasterTool {
    public SpellLyricsItem(Properties properties) {
        super(properties);
    }

    public SpellLyricsItem() {
        super(ItemsRegistry.defaultItemProperties().component(DataComponentRegistry.SPELL_CASTER, new SpellCaster()));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        AbstractCaster<?> caster = getSpellCaster(pStack);
        return caster.getSpellName().isEmpty() ? super.getName(pStack) : Component.literal(caster.getSpellName());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltip2, @NotNull TooltipFlag flagIn) {
        stack.addToTooltip(DataComponentRegistry.SPELL_CASTER, context, tooltip2::add, flagIn);
        super.appendHoverText(stack, context, tooltip2, flagIn);
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack pStack) {
        AbstractCaster<?> caster = getSpellCaster(pStack);
        if (Config.GLYPH_TOOLTIPS.get() && !Screen.hasShiftDown() && !caster.isSpellHidden() && !caster.getSpell().isEmpty())
            return Optional.of(new SpellTooltip(caster));
        return Optional.empty();
    }

    public boolean onScribe(Level world, BlockPos pos, Player player, InteractionHand handIn, ItemStack tableStack) {
        ItemStack heldStack = player.getItemInHand(handIn);
        AbstractCaster<?> tableCaster = SpellCasterRegistry.from(tableStack);
        if (!((heldStack.getItem() instanceof SpellBook) || (heldStack.getItem() instanceof SpellParchment) || (heldStack.getItem() == ItemsRegistry.MANIPULATION_ESSENCE.asItem())))
            return false;
        if (tableCaster == null) {
            return false;
        }

        AbstractCaster<?> heldCaster = SpellCasterRegistry.from(heldStack);
        Spell spell = new Spell();
        // If the held item is a manipulation essence, set the spell to a random hidden spell
        if (heldStack.getItem() == ItemsRegistry.MANIPULATION_ESSENCE.asItem()) {
            // Thanks mojang
            String[] words = new String[]{"the", "elder", "scrolls", "klaatu", "berata", "niktu", "xyzzy", "bless", "curse", "light", "darkness", "fire", "air", "earth", "water", "hot", "dry", "cold", "wet", "ignite", "snuff", "embiggen", "twist", "shorten", "stretch", "fiddle", "destroy", "imbue", "galvanize", "enchant", "free", "limited", "range", "of", "towards", "inside", "sphere", "cube", "self", "other", "ball", "mental", "physical", "grow", "shrink", "demon", "elemental", "spirit", "animal", "creature", "beast", "humanoid", "undead", "fresh", "stale", "phnglui", "mglwnafh", "cthulhu", "rlyeh", "wgahnagl", "fhtagn", "baguette"};
            // Pick between 3 and 5 words
            int numWords = world.random.nextInt(3) + 3;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numWords; i++) {
                sb.append(words[world.random.nextInt(words.length)]).append(" ");
            }
            tableCaster.setHidden(true)
                    .setHiddenRecipe(sb.toString())
                    .saveToStack(tableStack);
            PortUtil.sendMessageNoSpam(player, Component.translatable("ars_nouveau.spell_hidden"));
            return true;
        }
        // If the held caster is not null, set the spell to the held caster's spell
        if (heldCaster != null) {
            spell = heldCaster.getSpell();
            tableCaster.setColor(heldCaster.getColor())
                    .setFlavorText(heldCaster.getFlavorText())
                    .setSpellName(heldCaster.getSpellName())
                    .setSound(heldCaster.getCurrentSound())
                    .saveToStack(tableStack);

        }
        // modify the spell (ex. add Amplify with Enchanter Sword) if valid and then send the appropriate message
        if (isScribedSpellValid(tableCaster, player, handIn, tableStack, spell)) {
            var mutableSpell = spell.mutable();
            scribeModifiedSpell(tableCaster, player, handIn, tableStack, mutableSpell);
            tableCaster.setSpell(mutableSpell.immutable()).saveToStack(tableStack);
            sendSetMessage(player);
            return true;
        }
        sendInvalidMessage(player);
        return false;
    }
}
