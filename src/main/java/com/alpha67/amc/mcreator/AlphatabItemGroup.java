
package com.alpha67.amc.mcreator;

import com.alpha67.amc.vultorio.init.ItemInitVultorio;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import com.alpha67.amc.AmcModElements;

@AmcModElements.ModElement.Tag
public class AlphatabItemGroup extends AmcModElements.ModElement {
	public AlphatabItemGroup(AmcModElements instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabalphatab") {
			@OnlyIn(Dist.CLIENT)

			@Override
			public ItemStack makeIcon() {
				return ItemInitVultorio.alpharium_ingot.get().getDefaultInstance();
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
