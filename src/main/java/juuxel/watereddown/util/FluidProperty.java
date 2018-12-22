/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.util;

import com.google.common.collect.Sets;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.AbstractProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class FluidProperty extends AbstractProperty<FluidProperty.Wrapper> {
    public static final Wrapper EMPTY = new Wrapper(Fluids.EMPTY);
    public static final Wrapper WATER = new Wrapper(Fluids.WATER);
    public static final Wrapper LAVA = new Wrapper(Fluids.LAVA);
    public static final FluidProperty VANILLA_FLUIDS = new FluidProperty(
            "fluid",
            () -> Sets.newHashSet(FluidProperty.WATER, FluidProperty.LAVA, FluidProperty.EMPTY)
    );

    private final Supplier<Collection<Wrapper>> fluids;

    public FluidProperty(String var1, Supplier<Collection<Wrapper>> fluids) {
        super(var1, Wrapper.class);
        this.fluids = fluids;
    }

    public FluidProperty(String var1) {
        this(var1, () -> Registry.FLUID.stream().map(Wrapper::new).collect(Collectors.toList()));
    }

    @Override
    public Collection<Wrapper> getValues() {
        return fluids.get();
    }

    @Override
    public Optional<Wrapper> getValue(String var1) {
        try {
            int underscore = var1.indexOf('_');
            int namespaceLength = Integer.parseInt(var1.substring(0, underscore));
            String namespace = var1.substring(underscore + 1, underscore + 1 + namespaceLength);
            String path = var1.substring(underscore + 1 + namespaceLength);
            Identifier id = new Identifier(namespace, path);
            if (Registry.FLUID.contains(id)) {
                return Optional.of(new Wrapper(Registry.FLUID.get(id)));
            }
        } catch (Exception e) {}

        return Optional.empty();
    }

    @Override
    public String getValueAsString(Wrapper var1) {
        Identifier id = Registry.FLUID.getId(var1.fluid);
        return String.format("%d_%s%s", id.getNamespace().length(), id.getNamespace(), id.getPath());
    }

    public static final class Wrapper implements Comparable<Wrapper> {
        private final Fluid fluid;

        public Wrapper(Fluid fluid) {
            this.fluid = fluid;
        }

        public Fluid getFluid() {
            return fluid;
        }

        @Override
        public int compareTo(Wrapper o) {
            return Integer.compare(getId(fluid), getId(o.fluid));
        }

        private int getId(Fluid fluid) {
            return fluid != null ? Registry.FLUID.getRawId(fluid) : -1;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(fluid);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Wrapper && Objects.equals(((Wrapper) obj).fluid, fluid);
        }

        @Override
        public String toString() {
            return Registry.FLUID.getId(fluid).toString();
        }
    }
}
