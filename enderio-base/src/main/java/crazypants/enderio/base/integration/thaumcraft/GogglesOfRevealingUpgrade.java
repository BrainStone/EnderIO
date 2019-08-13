package crazypants.enderio.base.integration.thaumcraft;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.enderio.core.common.util.NNList;

import crazypants.enderio.api.upgrades.IDarkSteelItem;
import crazypants.enderio.base.config.config.DarkSteelConfig;
import crazypants.enderio.base.handler.darksteel.AbstractUpgrade;
import crazypants.enderio.base.handler.darksteel.Rules;
import crazypants.enderio.base.lang.Lang;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class GogglesOfRevealingUpgrade extends AbstractUpgrade {

  private static final @Nonnull String UPGRADE_NAME = "gogglesOfRevealing";

  public static final @Nonnull GogglesOfRevealingUpgrade INSTANCE = new GogglesOfRevealingUpgrade();

  public static boolean isUpgradeEquipped(@Nonnull EntityPlayer player) {
    ItemStack helmet = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
    return GogglesOfRevealingUpgrade.INSTANCE.hasUpgrade(helmet);
  }

  public GogglesOfRevealingUpgrade() {
    super(UPGRADE_NAME, "enderio.darksteel.upgrade." + UPGRADE_NAME, DarkSteelConfig.gogglesOfRevealingCost);
  }

  @Override
  public boolean canAddToItem(@Nonnull ItemStack stack, @Nonnull IDarkSteelItem item) {
    if (!item.isForSlot(EntityEquipmentSlot.HEAD)) {
      return false;
    }
    return item.hasUpgradeCallbacks(this) && !hasUpgrade(stack, item);
  }

  @Override
  @Nonnull
  public List<IRule> getRules() {
    return new NNList<>(Rules.forSlot(EntityEquipmentSlot.HEAD), Rules.callbacksFor(this), Rules.itemTypeTooltip(EntityEquipmentSlot.HEAD));
  }

  @Override
  @Nonnull
  public List<Supplier<String>> getItemClassesForTooltip() {
    return new NNList<>(Lang.DSU_CLASS_ARMOR_HEAD::get);
  }

}
